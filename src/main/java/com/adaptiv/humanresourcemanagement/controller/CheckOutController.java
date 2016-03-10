package com.adaptiv.humanresourcemanagement.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adaptiv.humanresourcemanagement.dto.CheckOutDto;
import com.adaptiv.humanresourcemanagement.entity.CheckOut;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.RestApiException;
import com.adaptiv.humanresourcemanagement.mapper.CheckOutMapper;
import com.adaptiv.humanresourcemanagement.service.CheckOutService;

@RestController
@RequestMapping(value = "/api/checkouts")
public class CheckOutController {

	@Autowired
	private CheckOutService checkOutService;

	@Autowired
	private CheckOutMapper checkOutMapper;

	private Logger logger = Logger.getLogger(CheckOutController.class);

	@RequestMapping(method = RequestMethod.POST)
	public CheckOutDto createOne(@RequestBody CheckOutDto checkOutDto) throws RestApiException {
		try {
			CheckOut checkOutToPersist = checkOutMapper.fromDtoToEntity(checkOutDto);
			return checkOutMapper.fromEntityToDto(checkOutService.createOne(checkOutToPersist));
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_CHECKOUT, e);
			throw new RestApiException(ExceptionType.COULD_NOT_CHECKOUT, e);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<CheckOutDto> getAll() throws RestApiException {
		try {
			List<CheckOut> foundCheckOuts = checkOutService.getAll();
			return checkOutMapper.fromEntityToDto(foundCheckOuts);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_API, e);
			throw new RestApiException(ExceptionType.COULD_NOT_FIND_API, e);
		}
	}
}
