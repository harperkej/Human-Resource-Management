package com.adaptiv.humanresourcemanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adaptiv.humanresourcemanagement.dto.CheckOutDto;
import com.adaptiv.humanresourcemanagement.entity.CheckOut;

@Component
public class CheckOutMapper implements Mapper<CheckOut, CheckOutDto> {

	public CheckOut fromDtoToEntity(CheckOutDto dto) {
		CheckOut checkOut = new CheckOut();
		checkOut.setId(dto.getId());
		checkOut.setCheckIn(dto.getCheckIn());
		checkOut.setTime(dto.getTime());
		return checkOut;
	}

	public CheckOutDto fromEntityToDto(CheckOut entity) {
		CheckOutDto checkOutDto = new CheckOutDto();
		checkOutDto.setId(entity.getId());
		checkOutDto.setTime(entity.getTime());
		checkOutDto.setCheckIn(entity.getCheckIn());
		return checkOutDto;
	}

	public List<CheckOut> fromDtoToEntity(List<CheckOutDto> dtos) {
		List<CheckOut> checkOuts = new ArrayList<CheckOut>();
		for (CheckOutDto checkOutDto : dtos) {
			checkOuts.add(fromDtoToEntity(checkOutDto));
		}
		return checkOuts;
	}

	public List<CheckOutDto> fromEntityToDto(List<CheckOut> entities) {
		List<CheckOutDto> checkOutDtos = new ArrayList<CheckOutDto>();
		for (CheckOut checkOut : entities) {
			checkOutDtos.add(this.fromEntityToDto(checkOut));
		}
		return checkOutDtos;
	}

}
