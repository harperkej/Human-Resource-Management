package com.adaptiv.humanresourcemanagement.exception;

public class RepositoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public RepositoryException(ExceptionType exceptionType, Throwable exception) {
		super(exceptionType.getMessage(), exception);
		this.message = exceptionType.getMessage();
		this.code = exceptionType.getCode();
	}

	public RepositoryException(ExceptionType exceptionType) {
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
