package com.zkhy.officialWebsite.service;

import java.util.List;

import com.zkhy.officialWebsite.common.page.PageReq;
import com.zkhy.officialWebsite.common.page.PageRes;
import com.zkhy.officialWebsite.common.serverResponse.ServerResponse;
import com.zkhy.officialWebsite.model.SysUser;

public interface ISysUserService extends IBaseService<SysUser>{

	/**
	 * 登录
	 * @param sysUser
	 * @return
	 */
	ServerResponse<SysUser> login(SysUser sysUser);
	
	/**
	 * 注销登录
	 * @return
	 */
	ServerResponse<SysUser> logout();
	
	/**
	 * 注册普通管理员
	 * @param sysUser
	 * @return
	 */
	ServerResponse<SysUser> register(SysUser sysUser);
	
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	ServerResponse<SysUser> changePwd(String oldPwd, String newPwd);
	
	/**
	 * 超级管理员重置普通管理员密码
	 * @param sysUser
	 * @return
	 */
	ServerResponse<Object> changePwds(List<String> list);
	
	/**
	 * 分页查询普通管理员
	 * @param pageReq
	 * @return
	 */
	PageRes<SysUser> listSysUser(PageReq<SysUser> pageReq);
	
	/**
	 * 删除普通管理员
	 * @param sysUserIds
	 * @return
	 */
	ServerResponse<Object> delSysUser(List<String> sysUserIds);
}
