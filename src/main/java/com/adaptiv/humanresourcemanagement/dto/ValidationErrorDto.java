package com.adaptiv.humanresourcemanagement.dto;

import java.util.List;

public class ValidationErrorDto {

	private List<FieldErrorDto> fiedlError;

	public List<FieldErrorDto> getFiedlError() {
		return fiedlError;
	}

	public void setFiedlError(List<FieldErrorDto> fiedlError) {
		this.fiedlError = fiedlError;
	}

}
