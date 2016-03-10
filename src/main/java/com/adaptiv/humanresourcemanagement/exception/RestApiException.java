package com.adaptiv.humanresourcemanagement.exception;

public class RestApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public RestApiException(ExceptionType exceptionType, Throwable throwable) {
		super(exceptionType.getMessage(), throwable);
		this.code = exceptionType.getCode();
		this.message = exceptionType.getMessage();
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
