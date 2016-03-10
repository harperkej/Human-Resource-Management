package com.adaptiv.humanresourcemanagement.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adaptiv.humanresourcemanagement.dto.CheckInDto;
import com.adaptiv.humanresourcemanagement.entity.CheckIn;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.RestApiException;
import com.adaptiv.humanresourcemanagement.mapper.CheckInMapper;
import com.adaptiv.humanresourcemanagement.service.CheckInService;

@RestController
@RequestMapping(value = "/api/checkins")
public class CheckInController {

	@Autowired
	private CheckInService checkInService;

	@Autowired
	private CheckInMapper checkInMapper;

	private Logger logger = Logger.getLogger(CheckInService.class);

	@RequestMapping(method = RequestMethod.POST)
	public CheckInDto createOne(@Valid @RequestBody CheckInDto checkInDto) throws RestApiException {
		try {
			CheckIn checkInToPersist = checkInMapper.fromDtoToEntity(checkInDto);
			checkInService.createOne(checkInToPersist);
			return checkInMapper.fromEntityToDto(checkInToPersist);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_SAVE_API, e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CheckInDto findOneById(@PathVariable Long id) throws RestApiException {
		try {
			CheckIn foundCheckIn = checkInService.findOneById(id);
			return checkInMapper.fromEntityToDto(foundCheckIn);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}

	@RequestMapping(value = "/date", method = RequestMethod.GET)
	public List<CheckInDto> findCheckInsByDate(
			@RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS") Date date)
					throws RestApiException {
		try {
			List<CheckIn> foundCheckIns = checkInService.findChecksInByDate(date);
			return checkInMapper.fromEntityToDto(foundCheckIns);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CheckInDto> getAll() throws RestApiException {
		try {
			List<CheckIn> foundCheckIns = checkInService.getAll();
			return checkInMapper.fromEntityToDto(foundCheckIns);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}

	@RequestMapping(value = "/employeeid/{employeeid}")
	public List<CheckInDto> findCheckInsByEmployeId(@PathVariable("employeeid") Long employeeId)
			throws RestApiException {
		try {
			List<CheckIn> checkIns = checkInService.findCheckInsByEmployeeId(employeeId);
			return checkInMapper.fromEntityToDto(checkIns);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API,e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}

	@RequestMapping(value = "/betweentwodates", method = RequestMethod.GET)
	public List<CheckInDto> findCheckInsBetween2Dates(
			@RequestParam("startDate") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS") Date endDate)
					throws RestApiException {
		try {
			List<CheckIn> foundCheckInsBetween2Dates = checkInService.findCheckInsBetweeen2Dates(startDate, endDate);
			return checkInMapper.fromEntityToDto(foundCheckInsBetween2Dates);
		} catch (Exception e) {
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}
}
