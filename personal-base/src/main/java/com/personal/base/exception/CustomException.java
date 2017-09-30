package com.personal.base.exception;

/**
 * 自定义异常
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年9月21日/下午4:51:42
 * 
 */
public class CustomException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	public CustomException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
