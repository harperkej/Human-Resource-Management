package com.adaptiv.humanresourcemanagement.service;

import java.util.Date;
import java.util.List;

import com.adaptiv.humanresourcemanagement.entity.Employee;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;

public interface EmployeeService {

	public Employee findOneById(Long id) throws ServiceException;

	public Employee createOne(Employee employee) throws ServiceException;

	public Employee updateOne(Employee employee) throws ServiceException;

	public List<Employee> getAll() throws ServiceException;

	public List<Employee> findByDepartmentId(Long id) throws ServiceException;

	public long findNumWorkHoursOfEmployeeById(Date startDate, Date endDate, long id) throws ServiceException;
}
