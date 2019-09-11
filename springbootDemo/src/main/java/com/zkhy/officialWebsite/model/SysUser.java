package com.zkhy.officialWebsite.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="zk_sysuser")
public class SysUser {

	/**
	 * 用户ID
	 */
	@Id
	private String sysUserId;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String pwd;
	
	/**
	 * 角色：分为超级管理员【SYSADMIN】 和 普通管理员【ADMIN】
	 */
	private String role;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 修改时间
	 */
	private Date updatetime;
}
