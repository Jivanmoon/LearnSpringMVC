package com.jiayifan.ssm.exception;
/**
 * 系统自定义异常类
 * @author 贾一帆
 *
 */
public class CustomException extends Exception {
	private String message;
	public CustomException(String message) {
		super(message);
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
