package com.adaptiv.humanresourcemanagement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adaptiv.humanresourcemanagement.dto.DepartmentDto;
import com.adaptiv.humanresourcemanagement.entity.Department;

@Component
public class DepartmentMapper implements Mapper<Department, DepartmentDto> {

	public Department fromDtoToEntity(DepartmentDto dto) {
		Department department = new Department();
		department.setId(dto.getId());
		department.setName(dto.getName());
		department.setDescription(dto.getDescription());
		return department;
	}

	public DepartmentDto fromEntityToDto(Department entity) {
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setId(entity.getId());
		departmentDto.setName(entity.getName());
		departmentDto.setDescription(entity.getDescription());
		return departmentDto;
	}

	public List<Department> fromDtoToEntity(List<DepartmentDto> dtos) {
		List<Department> departments = new ArrayList<Department>();
		for (DepartmentDto departmentDto : dtos) {
			departments.add(fromDtoToEntity(departmentDto));
		}
		return departments;
	}

	public List<DepartmentDto> fromEntityToDto(List<Department> entities) {
		List<DepartmentDto> departmentDto = new ArrayList<DepartmentDto>();
		for (Department department : entities) {
			departmentDto.add(fromEntityToDto(department));
		}
		return departmentDto;
	}
}