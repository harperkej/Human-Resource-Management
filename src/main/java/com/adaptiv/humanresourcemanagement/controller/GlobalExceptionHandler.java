package com.adaptiv.humanresourcemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.adaptiv.humanresourcemanagement.dto.ValidationErrorDto;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.InvalidCheckOutException;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;
import com.adaptiv.humanresourcemanagement.exception.RestApiException;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;
import com.adaptiv.humanresourcemanagement.tool.ExceptionDetail;
import com.adaptiv.humanresourcemanagement.tool.ResolveLocalizedErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ResolveLocalizedErrorMessage resolveLocalizedErrorMessage;

	@ExceptionHandler(value = RestApiException.class)
	public ResponseEntity<ExceptionDetail> handleRestApiException(RestApiException restApiException) {
		ResponseEntity<ExceptionDetail> response;
		ExceptionDetail exceptionDetail = new ExceptionDetail();
		exceptionDetail.setCode(restApiException.getCode());
		exceptionDetail.setMessage(restApiException.getMessage());
		if (restApiException.getCode().equals(ExceptionType.COULD_NOT_FIND_API.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_FOUND);
		} else if (restApiException.getCode().equals(ExceptionType.COULD_NOT_SAVE_API.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.CONFLICT);
		} else if (restApiException.getCode().equals(ExceptionType.COULD_NOT_UPDATE_API.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_MODIFIED);
		} else if (restApiException.getCode().equals(ExceptionType.CHECKEDOUT_CHECKIN.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
		} else if (restApiException.getCode().equals(ExceptionType.COULD_NOT_CHECKOUT.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
		} else {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ExceptionHandler(value = RepositoryException.class)
	public ResponseEntity<ExceptionDetail> handleRepositoryExcetion(RepositoryException repositoryException) {
		ResponseEntity<ExceptionDetail> response;
		ExceptionDetail exceptionDetail = new ExceptionDetail();
		exceptionDetail.setCode(repositoryException.getCode());
		exceptionDetail.setMessage(repositoryException.getMessage());
		if (repositoryException.getCode().equals(ExceptionType.COULD_NOT_FIND_DB.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_FOUND);
		} else if (repositoryException.getCode().equals(ExceptionType.COULD_NOT_SAVE_DB.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.CONFLICT);
		} else if (repositoryException.getCode().equals(ExceptionType.COULD_NOT_UPDATE_DB.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_MODIFIED);
		} else if (repositoryException.getCode().equals(ExceptionType.CHECKEDOUT_CHECKIN.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
		} else if (repositoryException.getCode().equals(ExceptionType.COULD_NOT_CHECKOUT.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
		} else {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<ExceptionDetail> handleServiceException(ServiceException serviceException) {
		ResponseEntity<ExceptionDetail> response;
		ExceptionDetail exceptionDetail = new ExceptionDetail();
		exceptionDetail.setCode(serviceException.getCode());
		exceptionDetail.setMessage(serviceException.getMessage());
		if (serviceException.getCode().equals(ExceptionType.COULD_NOT_FIND_SERVICE.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_FOUND);
		} else if (serviceException.getCode().equals(ExceptionType.COULD_NOT_SAVE_SERVICE.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.CONFLICT);
		} else if (serviceException.getCode().equals(ExceptionType.COULD_NOT_UPDATE_SERVICE.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_MODIFIED);
		} else if (serviceException.getCode().equals(ExceptionType.CHECKEDOUT_CHECKIN.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
		} else if (serviceException.getCode().equals(ExceptionType.COULD_NOT_CHECKOUT.getCode())) {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
		} else {
			response = new ResponseEntity<ExceptionDetail>(exceptionDetail, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationErrorDto handleValidationErrors(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		System.out.println("Is it coming here ???");
		List<FieldError> errors = bindingResult.getFieldErrors();
		return resolveLocalizedErrorMessage.processFieldErrors(errors);
	}

	@ExceptionHandler(value = InvalidCheckOutException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public ExceptionDetail handleCheckOutException(InvalidCheckOutException invalidCheckOutException) {
		ExceptionDetail exceptionDetail = new ExceptionDetail();
		exceptionDetail.setCode(invalidCheckOutException.getCode());
		exceptionDetail.setMessage(invalidCheckOutException.getMessage());
		return exceptionDetail;
	}

}
