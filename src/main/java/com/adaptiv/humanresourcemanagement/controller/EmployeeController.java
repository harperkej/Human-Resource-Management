package com.adaptiv.humanresourcemanagement.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adaptiv.humanresourcemanagement.dto.EmployeeDto;
import com.adaptiv.humanresourcemanagement.entity.Employee;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.RestApiException;
import com.adaptiv.humanresourcemanagement.mapper.EmployeeMapper;
import com.adaptiv.humanresourcemanagement.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	private EmployeeMapper employeeMapper;

	private Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
		this.employeeService = employeeService;
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public EmployeeDto createOne(@Valid @RequestBody EmployeeDto employeeDto) throws RestApiException {
		try {
			Employee employeeToPersist = employeeMapper.fromDtoToEntity(employeeDto);
			employeeService.createOne(employeeToPersist);
			return employeeMapper.fromEntityToDto(employeeToPersist);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_SAVE_API, e);
		}
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT)
	public EmployeeDto updateOne(@Valid @RequestBody EmployeeDto employeeDto) throws RestApiException {
		try {
			Employee employeeToUpdate = employeeMapper.fromDtoToEntity(employeeDto);
			return employeeMapper.fromEntityToDto(employeeService.updateOne(employeeToUpdate));
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_UPDATE_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_UPDATE_API, e);
		}
	}

	@ResponseStatus(value = HttpStatus.FOUND)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public EmployeeDto findOne(@PathVariable Long id) throws RestApiException {
		try {
			Employee foundEmployee = employeeService.findOneById(id);
			return employeeMapper.fromEntityToDto(foundEmployee);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<EmployeeDto> getAll() throws RestApiException {
		try {
			List<Employee> foundEmployee = employeeService.getAll();
			return employeeMapper.fromEntityToDto(foundEmployee);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}

	@ResponseStatus(value = HttpStatus.FOUND)
	@RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
	public List<EmployeeDto> findByDepartmentId(@PathVariable("id") Long departmentId) throws RestApiException {
		try {
			List<Employee> foundEmployees = employeeService.findByDepartmentId(departmentId);
			return employeeMapper.fromEntityToDto(foundEmployees);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}

	@RequestMapping(value = "/{id}/hours", method = RequestMethod.GET)
	public long finfNumberOfWorkHoursBetween2DateById(
			@RequestParam("startDate") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS") Date endDate,
			@PathVariable("id") Long id) throws RestApiException {
		try {
			return employeeService.finfNumberOfWorkHoursBetween2DateById(startDate, endDate, id);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}
}