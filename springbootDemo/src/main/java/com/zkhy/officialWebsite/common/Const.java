package com.zkhy.officialWebsite.common;

public class Const {

	//Session常量
	public static final String CURRENT_SYSUSER = "current_sysuser";
	
	//默认密码
	public static final String INDEX_PWD = "123456";
	
	
	
	//TOMCAT 安装目录
	public static final String TOMCAT_PATH  = System.getProperty("catalina.home");
	//RESOURCE 静态资源存放路径
	public static final String RESOURCE_PATH = TOMCAT_PATH +"/webapps/resources/";
}
