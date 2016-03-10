package com.adaptiv.humanresourcemanagement.repository;

import java.util.List;

import com.adaptiv.humanresourcemanagement.entity.CheckOut;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;

public interface CheckOutRepository {

	public CheckOut createOne(CheckOut checkOut) throws RepositoryException;

	public CheckOut findCheckOutByCheckInId(Long checkInId) throws RepositoryException;

	public List<CheckOut> getAll() throws RepositoryException;
	
	public List<CheckOut> findAllCheckOutsOfAnEmployeeByEmployeeId(Long emloyeeId) throws RepositoryException;

}
