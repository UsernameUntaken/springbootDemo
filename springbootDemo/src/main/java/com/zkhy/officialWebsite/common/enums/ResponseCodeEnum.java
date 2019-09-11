package com.zkhy.officialWebsite.common.enums;

public enum ResponseCodeEnum {
	SUCCESS(200,"SUCCESS"),
	NEED_LOGIN(201, "用户未登录或登录超时，请重新登录"),
	REQUIRED_ARGUMENT(202, "缺少必填参数:"),
	INVALID_PARAM(203, "无效参数:"),
	ERROR(100,"ERROR"),
	UNKNOWN_ACCOUNT(101,"用户名错误");
	
	private final int code;
    private final String desc;


    ResponseCodeEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
