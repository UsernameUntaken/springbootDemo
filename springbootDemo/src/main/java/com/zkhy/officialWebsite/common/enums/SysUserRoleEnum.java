package com.zkhy.officialWebsite.common.enums;

public enum SysUserRoleEnum {
	SYSADMIN("0","超级管理员"),
	ADMIN("1", "普通管理员");
	
	private final String code;
    private final String desc;


    SysUserRoleEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
