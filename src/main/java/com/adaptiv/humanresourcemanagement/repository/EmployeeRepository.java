package com.adaptiv.humanresourcemanagement.repository;

import java.util.List;

import com.adaptiv.humanresourcemanagement.entity.Employee;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;

public interface EmployeeRepository {

	public Employee createOne(Employee employee) throws RepositoryException;

	public Employee findOneById(Long id) throws RepositoryException;

	public Employee updateOne(Employee employee) throws RepositoryException;

	public List<Employee> findByDepartmentId(Long id) throws RepositoryException;

	public List<Employee> getAll() throws RepositoryException;

}
