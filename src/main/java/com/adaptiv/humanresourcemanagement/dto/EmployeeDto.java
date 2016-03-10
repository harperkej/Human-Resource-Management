package com.adaptiv.humanresourcemanagement.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.adaptiv.humanresourcemanagement.entity.Department;

public class EmployeeDto {

	private Long id;

	@Size(min = 5, message = "The name should be at leas 5 characters long")
	private String name;

	@Size(min = 5, message = "The username should be at leas 5 characters long")
	private String username;

	@Size(min = 6, message = "The password should be at leas 6 characters long")
	private String password;

	@Email(message = "Please type an email")
	private String email;

	@NotNull
	private Department department;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
