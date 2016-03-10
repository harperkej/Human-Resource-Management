package com.adaptiv.humanresourcemanagement.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.adaptiv.humanresourcemanagement.entity.Employee;
import com.adaptiv.humanresourcemanagement.tool.DateDeserializer;
import com.adaptiv.humanresourcemanagement.tool.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CheckInDto {

	private Long id;

	@NotNull(message = "Please specify the time for checkin")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonDeserialize(using = DateDeserializer.class)
	@JsonSerialize(using = DateSerializer.class)
	private Date time;

	@NotNull(message = "Please scpecify the correspondent employee to checkin")
	private Employee employee;

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}