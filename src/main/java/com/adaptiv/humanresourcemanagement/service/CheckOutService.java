package com.adaptiv.humanresourcemanagement.service;

import java.util.List;

import com.adaptiv.humanresourcemanagement.entity.CheckOut;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;

public interface CheckOutService {

	public CheckOut createOne(CheckOut checkOut) throws ServiceException;

	public List<CheckOut> getAll() throws ServiceException;
}
