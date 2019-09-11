package com.zkhy.officialWebsite.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkhy.officialWebsite.common.page.PageReq;
import com.zkhy.officialWebsite.common.page.PageRes;
import com.zkhy.officialWebsite.common.serverResponse.RequestDTO;
import com.zkhy.officialWebsite.common.serverResponse.ServerResponse;
import com.zkhy.officialWebsite.model.SysUser;
import com.zkhy.officialWebsite.service.ISysUserService;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

public Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 登录
	 * @param member
	 * @return
	 */
	@PostMapping("/login")
	public ServerResponse<SysUser> login(@RequestBody SysUser sysUser){
		return sysUserService.login(sysUser);
	}
	
	/**
	 * 注册
	 * @param member
	 * @return
	 */
	@PostMapping("/register")
	public ServerResponse<SysUser> register(@RequestBody SysUser sysUser){
		return sysUserService.register(sysUser);
	}
	
	/**
	 * 注销登录
	 * @return
	 */
	@PostMapping("/logout")
	public ServerResponse<SysUser> logout(){
		return sysUserService.logout();
	}
	
	/**
	 * 修改密码
	 * @param requestDTO
	 * @return
	 */
	@PostMapping("/changePwd")
	public ServerResponse<SysUser> changePwd(@RequestBody RequestDTO requestDTO){
		String oldPwd = requestDTO.getParam("oldPwd");
		String newPwd = requestDTO.getParam("newPwd");
		return sysUserService.changePwd(oldPwd, newPwd);
	}
	
	/**
	 * 重置密码
	 * @param sysUserIds
	 * @return
	 */
	@PostMapping("/changePwds")
	public ServerResponse<Object> changePwds(@RequestBody RequestDTO requestDTO){
		List<String> list = requestDTO.getListParam("sysUserIds", String.class);
		return sysUserService.changePwds(list);
	}
	
	/**
	 * 分页查询普通管理员
	 * @param pageReq
	 * @return
	 */
	@PostMapping("/listSysUser")
	public ServerResponse<PageRes<SysUser>> listSysUser(@RequestBody PageReq<SysUser> pageReq) {
		return ServerResponse.createBySuccess("查询成功", sysUserService.listSysUser(pageReq));
	}
	
	/**
	 * 删除管理员
	 * @param requestDTO
	 * @return
	 */
	@PostMapping("/del")
	public ServerResponse<Object> delSysUser(@RequestBody RequestDTO requestDTO) {
		List<String> list = requestDTO.getListParam("sysUserIds", String.class);
		return sysUserService.delSysUser(list);
	}
}
