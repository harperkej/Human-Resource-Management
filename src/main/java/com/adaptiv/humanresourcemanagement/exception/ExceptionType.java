package com.adaptiv.humanresourcemanagement.exception;

public enum ExceptionType {

	COULD_NOT_SAVE_DB("DB001", "Could not save to database"), 
	COULD_NOT_FIND_DB("DB002","Colud not retreive data from database"),
	COULD_NOT_UPDATE_DB("DB003", "Could not update data to database"),
	
	COULD_NOT_SAVE_SERVICE("SV001", "Could not save to database"), 
	COULD_NOT_UPDATE_SERVICE("SV002", "Could not update to database"), 
	COULD_NOT_FIND_SERVICE("SV003",	"Could not retreive from database"), 
	
	COULD_NOT_SAVE_API("API001", "Could not save to database"),
	COULD_NOT_UPDATE_API("API002","Could not update to database"), 
	COULD_NOT_FIND_API("API003", "Could not retreive from database"),

	COULD_NOT_CHECKOUT("SV005", "Could not checkout"),
	CHECKEDOUT_CHECKIN("SV006","This checking is alreay checkout");
	

	private String code;
	private String message;

	private ExceptionType(String code, String message) {
		this.code = code;
		this.message = message;
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
