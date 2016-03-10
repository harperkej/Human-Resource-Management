package com.adaptiv.humanresourcemanagement.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaptiv.humanresourcemanagement.entity.CheckIn;
import com.adaptiv.humanresourcemanagement.entity.CheckOut;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.InvalidCheckOutException;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;
import com.adaptiv.humanresourcemanagement.repository.CheckOutRepository;

@Service
@Transactional
public class CheckOutServiceImpl implements CheckOutService {

	@Autowired
	private CheckOutRepository checkOutRepository;

	private Logger logger = Logger.getLogger(CheckOutService.class);

	public CheckOut createOne(CheckOut checkOut) throws ServiceException {
		try {
			if (isThereAlreadyACheckOutForThisCheckIn(checkOut.getCheckIn())) {
				throw new InvalidCheckOutException(ExceptionType.CHECKEDOUT_CHECKIN);
			}
			return checkOutRepository.createOne(checkOut);
		} catch (InvalidCheckOutException e) {
			logger.error(ExceptionType.CHECKEDOUT_CHECKIN);
			throw new ServiceException(ExceptionType.CHECKEDOUT_CHECKIN, e);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_CHECKOUT, e);
			throw new ServiceException(ExceptionType.COULD_NOT_CHECKOUT, e);
		}
	}

	public List<CheckOut> getAll() throws ServiceException {
		try {
			return checkOutRepository.getAll();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

	protected boolean isThereAlreadyACheckOutForThisCheckIn(CheckIn checkIn) {
		try {
			CheckOut checkOut = checkOutRepository.findCheckOutByCheckInId(checkIn.getId());
			if (checkOut != null) {
				return true;
			}
		} catch (RepositoryException e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			return false;
		}
		return false;
	}
}