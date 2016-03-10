package com.adaptiv.humanresourcemanagement.service;

import java.util.List;

import com.adaptiv.humanresourcemanagement.entity.Department;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;

public interface DepartmentService {

	public Department createOne(Department department) throws ServiceException;

	public Department updateOne(Department department) throws ServiceException;

	public Department findOneById(Long id) throws ServiceException;

	public List<Department> getAll() throws ServiceException;
}
