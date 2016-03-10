package com.adaptiv.humanresourcemanagement.dto;

import javax.validation.constraints.Size;

public class DepartmentDto {

	private Long id;

	@Size(min = 5, message = "The department name should be at least 5 characters long")
	private String name;

	@Size(min = 5, message = "The department name should be at least 5 characters long")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
