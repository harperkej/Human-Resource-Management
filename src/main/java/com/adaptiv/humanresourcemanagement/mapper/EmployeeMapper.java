package com.adaptiv.humanresourcemanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adaptiv.humanresourcemanagement.dto.EmployeeDto;
import com.adaptiv.humanresourcemanagement.entity.Employee;

@Component
public class EmployeeMapper implements Mapper<Employee, EmployeeDto> {

	public Employee fromDtoToEntity(EmployeeDto dto) {
		Employee employee = new Employee();
		employee.setId(dto.getId());
		employee.setName(dto.getName());
		employee.setUsername(dto.getUsername());
		employee.setEmail(dto.getEmail());
		employee.setPassword(dto.getPassword());
		employee.setDepartment(dto.getDepartment());
		return employee;
	}

	public EmployeeDto fromEntityToDto(Employee entity) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(entity.getId());
		employeeDto.setName(entity.getName());
		employeeDto.setEmail(entity.getEmail());
		employeeDto.setUsername(entity.getUsername());
		employeeDto.setPassword(entity.getPassword());
		employeeDto.setDepartment(entity.getDepartment());
		return employeeDto;
	}

	public List<Employee> fromDtoToEntity(List<EmployeeDto> dtos) {
		List<Employee> employees = new ArrayList<Employee>();
		for (EmployeeDto employeeDto : dtos) {
			employees.add(fromDtoToEntity(employeeDto));
		}
		return employees;
	}

	public List<EmployeeDto> fromEntityToDto(List<Employee> entities) {
		List<EmployeeDto> employeesDto = new ArrayList<EmployeeDto>();
		for (Employee employee : entities) {
			employeesDto.add(fromEntityToDto(employee));
		}
		return employeesDto;
	}
}
