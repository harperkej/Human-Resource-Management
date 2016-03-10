package com.adaptiv.humanresourcemanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaptiv.humanresourcemanagement.entity.CheckIn;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;
import com.adaptiv.humanresourcemanagement.repository.CheckInRepository;

@Service
@Transactional
public class CheckInServiceImpl implements CheckInService {

	@Autowired
	private CheckInRepository checkInRepository;

	private Logger logger = Logger.getLogger(CheckInServiceImpl.class);

	public CheckIn createOne(CheckIn checkIn) throws ServiceException {
		try {
			return checkInRepository.createOne(checkIn);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_SAVE_SERVICE, e);
		}
	}

	public CheckIn findOneById(Long id) throws ServiceException {
		try {
			return checkInRepository.findOneById(id);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

	public List<CheckIn> findChecksInByDate(Date date) throws ServiceException {
		try {
			List<CheckIn> checkIns = checkInRepository.getAll();
			List<CheckIn> checkInsEquallyByDateWithdate = new ArrayList<CheckIn>();
			for (CheckIn checkIn : checkIns) {
				if (checkIn.getTime().compareTo(date) == 0) {
					checkInsEquallyByDateWithdate.add(checkIn);
				}
			}
			return checkInsEquallyByDateWithdate;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

	public List<CheckIn> findCheckInsBetweeen2Dates(Date startDate, Date endDate) throws ServiceException {
		try {
			List<CheckIn> allCheckIns = checkInRepository.getAll();
			List<CheckIn> resultCheckIns = new ArrayList<CheckIn>();
			for (CheckIn checkIn : allCheckIns) {
				if (checkIn.getTime().compareTo(startDate) > 0 && checkIn.getTime().compareTo(endDate) < 0) {
					resultCheckIns.add(checkIn);
				}
			}
			return resultCheckIns;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

	public List<CheckIn> findCheckInsByEmployeeId(Long id) throws ServiceException {
		try {
			return checkInRepository.findCheckInsByEmployeeId(id);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

	public List<CheckIn> getAll() throws ServiceException {
		try {
			return checkInRepository.getAll();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}
}
