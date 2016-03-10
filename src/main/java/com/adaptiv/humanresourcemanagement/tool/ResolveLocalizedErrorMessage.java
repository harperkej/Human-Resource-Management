package com.adaptiv.humanresourcemanagement.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.adaptiv.humanresourcemanagement.dto.FieldErrorDto;
import com.adaptiv.humanresourcemanagement.dto.ValidationErrorDto;

@Component
public class ResolveLocalizedErrorMessage {

	@Autowired
	private MessageSource messageSource;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public String resolveLocalizedErrorMessge(FieldError fieldError) {
		Locale currentLocal = LocaleContextHolder.getLocale();
		return getMessageSource().getMessage(fieldError, currentLocal);
	}

	public ValidationErrorDto processFieldErrors(List<FieldError> errors) {
		ValidationErrorDto validationErrorDto = new ValidationErrorDto();
		List<FieldErrorDto> listOfFieldErrorDto = new ArrayList<FieldErrorDto>();
		FieldErrorDto fieldErrorDto;
		for (FieldError fieldError : errors) {
			String localizedErrorMessage = resolveLocalizedErrorMessge(fieldError);
			fieldErrorDto = new FieldErrorDto();
			fieldErrorDto.setField(fieldError.getField());
			fieldErrorDto.setMessage(localizedErrorMessage);
			listOfFieldErrorDto.add(fieldErrorDto);
		}
		validationErrorDto.setFiedlError(listOfFieldErrorDto);
		return validationErrorDto;
	}

}
