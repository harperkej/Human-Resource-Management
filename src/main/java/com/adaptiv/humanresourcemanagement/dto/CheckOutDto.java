package com.adaptiv.humanresourcemanagement.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.adaptiv.humanresourcemanagement.entity.CheckIn;
import com.adaptiv.humanresourcemanagement.tool.DateDeserializer;
import com.adaptiv.humanresourcemanagement.tool.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CheckOutDto {

	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss.SSS")
	@JsonSerialize(using = DateSerializer.class)
	@JsonDeserialize(using = DateDeserializer.class)
	private Date time;

	@NotNull(message = "Please specify the corresponding checkin")
	private CheckIn checkIn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public CheckIn getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}
}
