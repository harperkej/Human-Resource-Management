package com.adaptiv.humanresourcemanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adaptiv.humanresourcemanagement.dto.DepartmentDto;
import com.adaptiv.humanresourcemanagement.entity.Department;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.RestApiException;
import com.adaptiv.humanresourcemanagement.mapper.DepartmentMapper;
import com.adaptiv.humanresourcemanagement.service.DepartmentService;

@RestController
@RequestMapping(value = "/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DepartmentMapper departmentMapper;

	private Logger logger = Logger.getLogger(DepartmentController.class);

	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public DepartmentDto createDepartmentDto(@Valid @RequestBody DepartmentDto dto) throws RestApiException {
		try {
			Department departmentToPersist = departmentMapper.fromDtoToEntity(dto);
			departmentService.createOne(departmentToPersist);
			return departmentMapper.fromEntityToDto(departmentToPersist);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_SAVE_API, e);
		}
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT)
	public DepartmentDto updateOne(@Valid @RequestBody DepartmentDto departmentDto) throws RestApiException {
		try {
			Department departmentToUpdate = departmentMapper.fromDtoToEntity(departmentDto);
			return departmentMapper.fromEntityToDto(departmentService.updateOne(departmentToUpdate));
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_UPDATE_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_UPDATE_API, e);
		}
	}

	@ResponseStatus(value = HttpStatus.FOUND)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DepartmentDto findOneById(@PathVariable Long id) throws RestApiException {
		try {
			return departmentMapper.fromEntityToDto(departmentService.findOneById(id));
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<DepartmentDto> getAll() throws RestApiException {
		try {
			return departmentMapper.fromEntityToDto(departmentService.getAll());
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}
}