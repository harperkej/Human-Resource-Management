package com.adaptiv.humanresourcemanagement.exception;

public class InvalidCheckOutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8296254274015420272L;

	private String code;
	private String message;

	public InvalidCheckOutException(ExceptionType exceptionType, Throwable exception) {
		super(exceptionType.getMessage(), exception);
		this.message = exceptionType.getMessage();
		this.code = exceptionType.getCode();
	}

	public InvalidCheckOutException(ExceptionType exceptionType) {
		this.message = exceptionType.getMessage();
		this.code = exceptionType.getCode();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
