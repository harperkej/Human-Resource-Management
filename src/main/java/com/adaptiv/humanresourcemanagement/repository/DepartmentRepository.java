package com.adaptiv.humanresourcemanagement.repository;

import java.util.List;

import com.adaptiv.humanresourcemanagement.entity.Department;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;

public interface DepartmentRepository {

	public Department findOneById(Long id) throws RepositoryException;

	public Department createOne(Department departmen) throws RepositoryException;

	public Department updateOne(Department department) throws RepositoryException;

	public List<Department> getAll() throws RepositoryException;
}
