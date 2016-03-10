package com.adaptiv.humanresourcemanagement.service;

import java.util.Date;
import java.util.List;

import com.adaptiv.humanresourcemanagement.entity.CheckIn;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;

public interface CheckInService {

	public CheckIn createOne(CheckIn checkIn) throws ServiceException;

	public CheckIn findOneById(Long id) throws ServiceException;

	public List<CheckIn> findChecksInByDate(Date date) throws ServiceException;

	public List<CheckIn> findCheckInsBetweeen2Dates(Date startDate, Date endDate) throws ServiceException;

	public List<CheckIn> findCheckInsByEmployeeId(Long id) throws ServiceException;

	public List<CheckIn> getAll() throws ServiceException;

}
