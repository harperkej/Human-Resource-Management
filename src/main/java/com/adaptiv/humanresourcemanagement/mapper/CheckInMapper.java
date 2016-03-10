package com.adaptiv.humanresourcemanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adaptiv.humanresourcemanagement.dto.CheckInDto;
import com.adaptiv.humanresourcemanagement.entity.CheckIn;

@Component
public class CheckInMapper implements Mapper<CheckIn, CheckInDto> {

	public CheckIn fromDtoToEntity(CheckInDto dto) {
		CheckIn checkIn = new CheckIn();
		checkIn.setId(dto.getId());
		checkIn.setTime(dto.getTime());
		checkIn.setEmployee(dto.getEmployee());
		return checkIn;
	}

	public CheckInDto fromEntityToDto(CheckIn entity) {
		CheckInDto checkInDto = new CheckInDto();
		checkInDto.setId(entity.getId());
		checkInDto.setEmployee(entity.getEmployee());
		checkInDto.setTime(entity.getTime());
		return checkInDto;
	}

	public List<CheckIn> fromDtoToEntity(List<CheckInDto> dtos) {
		List<CheckIn> checkIns = new ArrayList<CheckIn>();
		for (CheckInDto checkInDto : dtos) {
			checkIns.add(this.fromDtoToEntity(checkInDto));
		}
		return checkIns;
	}

	public List<CheckInDto> fromEntityToDto(List<CheckIn> entities) {
		List<CheckInDto> checkInDtos = new ArrayList<CheckInDto>();
		for (CheckIn checkIn : entities) {
			checkInDtos.add(fromEntityToDto(checkIn));
		}
		return checkInDtos;
	}
}
