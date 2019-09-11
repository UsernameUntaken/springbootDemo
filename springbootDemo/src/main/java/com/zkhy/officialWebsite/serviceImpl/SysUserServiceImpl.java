package com.zkhy.officialWebsite.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkhy.officialWebsite.common.Const;
import com.zkhy.officialWebsite.common.enums.ResponseCodeEnum;
import com.zkhy.officialWebsite.common.enums.SysUserRoleEnum;
import com.zkhy.officialWebsite.common.page.PageReq;
import com.zkhy.officialWebsite.common.page.PageRes;
import com.zkhy.officialWebsite.common.serverResponse.ServerResponse;
import com.zkhy.officialWebsite.exception.ServiceException;
import com.zkhy.officialWebsite.mapper.SysUserMapper;
import com.zkhy.officialWebsite.model.SysUser;
import com.zkhy.officialWebsite.service.ISysUserService;
import com.zkhy.officialWebsite.util.MD5Util;
import com.zkhy.officialWebsite.util.SerialUtil;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService{

	@Override
	public ServerResponse<SysUser> login(SysUser sysUser) {
		SysUser sys = new SysUser();
		sys.setUsername(sysUser.getUsername());
		sys = selectOne(sys);
		if(null==sys) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"用户名错误");
		}
		if(!MD5Util.MD5EncodeUtf8(sysUser.getPwd()).equals(sys.getPwd())) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"密码错误");
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.setAttribute(Const.CURRENT_SYSUSER, sys);
		sys.setPwd("");
		return ServerResponse.createBySuccess("登录成功", sys);
	}

	@Override
	public ServerResponse<SysUser> logout() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		SysUser mem = (SysUser) session.getAttribute(Const.CURRENT_SYSUSER);
		if(null!=mem) {
			session.removeAttribute(Const.CURRENT_SYSUSER);
		}
		return ServerResponse.createBySuccessMessage("注销登录成功");
	}

	@Override
	public ServerResponse<SysUser> register(SysUser sysUser) {
		SysUser sys= new SysUser();
		sys.setUsername(sysUser.getUsername());
		List<SysUser> sysList = select(sys);
		if(sysList.size()>0) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"此用户名已被注册");
		}
		sys.setSysUserId(SerialUtil.getUid());
		String pwd = sysUser.getPwd();
		if(StringUtils.isBlank(pwd)) {
			pwd = Const.INDEX_PWD;
		}
		sys.setPwd(MD5Util.MD5EncodeUtf8(pwd));
		sys.setRole(SysUserRoleEnum.ADMIN.getCode());
		sys.setCreatetime(new Date());
		if(!insert(sys)) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"注册失败");
		}
		return ServerResponse.createBySuccess("注册成功", sys);
	}

	@Override
	public ServerResponse<SysUser> changePwd(String oldPwd, String newPwd) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		SysUser sys = (SysUser) session.getAttribute(Const.CURRENT_SYSUSER);
		SysUser sysUser = selectByPrimaryKey(sys.getSysUserId());
		if(!sysUser.getPwd().equals(MD5Util.MD5EncodeUtf8(oldPwd))) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"原密码输入错误");
		}
		sysUser.setPwd(MD5Util.MD5EncodeUtf8(newPwd));
		sysUser.setUpdatetime(new Date());
		if(!updateByPrimaryKeySelective(sysUser)) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"密码修改失败");
		}
		//密码修改成功后则将session的CURRENT_SYSUSER置空，并由前端页面控制跳转到登录页
		session.removeAttribute(Const.CURRENT_SYSUSER);
		return ServerResponse.createBySuccessMessage("密码修改成功");
	}

	@Override
	public ServerResponse<Object> changePwds(List<String> list) {
		if(null==list || list.size()<1) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"参数错误");
		}
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		SysUser sysUser = (SysUser) session.getAttribute(Const.CURRENT_SYSUSER);
		if(!SysUserRoleEnum.SYSADMIN.getCode().equals(sysUser.getRole())) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"非超级管理员，非法操作");
		}
		int pwds_f = baseMapper.changePwds(MD5Util.MD5EncodeUtf8(Const.INDEX_PWD), list);
		if(pwds_f<=0) {
				throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"密码重置失败");
		}
		return ServerResponse.createBySuccessMessage("密码重置成功");
	}

	@Override
	public PageRes<SysUser> listSysUser(PageReq<SysUser> pageReq) {
		PageRes<SysUser> pageRes = null;
		if(null == pageReq.getEnity()) {
			return pageRes;
		}
		if(null == pageReq.getPageNum()) {
			pageReq.setPageNum(1);
		}
		if(null == pageReq.getPageSize()) {
			pageReq.setPageSize(10);
		}
		PageHelper.startPage(pageReq.getPageNum(), pageReq.getPageSize());
		pageRes = new PageRes<SysUser>();
		pageReq.getEnity().setRole(SysUserRoleEnum.ADMIN.getCode());
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(baseMapper.listSysUser(pageReq.getEnity()));
		pageRes.setTotal(pageInfo.getTotal());
		pageRes.setList(pageInfo.getList());
		return pageRes;
	}

	@Override
	public ServerResponse<Object> delSysUser(List<String> sysUserIds) {
		boolean delF = baseMapper.delSysUser(sysUserIds) > 0 ? true : false;
		if(!delF) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(),"删除失败");
		}
		return ServerResponse.createBySuccessMessage("删除成功");
	}

}
