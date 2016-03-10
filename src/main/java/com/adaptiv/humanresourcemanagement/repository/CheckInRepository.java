package com.adaptiv.humanresourcemanagement.repository;

import java.util.List;

import com.adaptiv.humanresourcemanagement.entity.CheckIn;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;

public interface CheckInRepository {

	public CheckIn createOne(CheckIn checkIn) throws RepositoryException;

	public CheckIn findOneById(Long id) throws RepositoryException;

	public List<CheckIn> findCheckInsByEmployeeId(Long employeeId) throws RepositoryException;

	public List<CheckIn> getAll() throws RepositoryException;

}
