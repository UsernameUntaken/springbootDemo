package com.zkhy.officialWebsite.exception;

public class InterceptorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2102396250051688008L;
	private int code;
	private String errorMsg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public InterceptorException(int code, String errorMsg) {
		super();
		this.code = code;
		this.errorMsg = errorMsg;
	}
}
