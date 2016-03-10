package com.adaptiv.humanresourcemanagement.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaptiv.humanresourcemanagement.entity.CheckOut;
import com.adaptiv.humanresourcemanagement.entity.Employee;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;
import com.adaptiv.humanresourcemanagement.repository.CheckOutRepository;
import com.adaptiv.humanresourcemanagement.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CheckOutRepository checkOutRepository;

	private Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

	public Employee findOneById(Long id) throws ServiceException {
		try {
			return employeeRepository.findOneById(id);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}

	public Employee createOne(Employee employee) throws ServiceException {
		try {
			return employeeRepository.createOne(employee);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(ExceptionType.COULD_NOT_SAVE_SERVICE, e);
		}
	}

	public Employee updateOne(Employee employee) throws ServiceException {
		try {
			return employeeRepository.updateOne(employee);
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(ExceptionType.COULD_NOT_SAVE_SERVICE, e);
		}
	}

	public List<Employee> getAll() throws ServiceException {
		try {
			return employeeRepository.getAll();
		} catch (Exception e) {
			logger.error(e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

	public List<Employee> findByDepartmentId(Long id) throws ServiceException {
		try {
			return employeeRepository.findByDepartmentId(id);
		} catch (Exception e) {
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

	public long finfNumberOfWorkHoursBetween2DateById(Date startDate, Date endDate, long id) throws ServiceException {
		try {
			long numberOfHoursAtWork = 0;
			Calendar checkInCalendar;
			Calendar checkOutCalendar;
			List<CheckOut> checkOuts = checkOutRepository.findAllCheckOutsOfAnEmployeeByEmployeeId(id);
			List<CheckOut> checkOutsBetween2Dates = new ArrayList<CheckOut>();
			for (CheckOut checkOut : checkOuts) {
				if (checkOut.getCheckIn().getTime().compareTo(startDate) >= 0
						&& checkOut.getTime().compareTo(endDate) <= 0) {
					checkOutsBetween2Dates.add(checkOut);
				}
			}
			for (CheckOut checkOut : checkOutsBetween2Dates) {

				checkInCalendar = Calendar.getInstance();
				checkOutCalendar = Calendar.getInstance();

				checkInCalendar.setTime(checkOut.getCheckIn().getTime());
				checkOutCalendar.setTime(checkOut.getTime());

				DateTime checkOutDateTime = new DateTime(checkOutCalendar.get(Calendar.YEAR) - 1900,
						checkOutCalendar.get(Calendar.MONTH), checkOutCalendar.get(Calendar.DAY_OF_MONTH),
						checkOutCalendar.get(Calendar.HOUR_OF_DAY), checkOutCalendar.get(Calendar.MINUTE),
						checkOutCalendar.get(Calendar.SECOND));

				DateTime checkInDateTime = new DateTime(checkInCalendar.get(Calendar.YEAR) - 1900,
						checkInCalendar.get(Calendar.MONTH), checkInCalendar.get(Calendar.DAY_OF_MONTH),
						checkInCalendar.get(Calendar.HOUR_OF_DAY), checkInCalendar.get(Calendar.MINUTE),
						checkInCalendar.get(Calendar.SECOND));

				Seconds seconds = Seconds.secondsBetween(checkInDateTime, checkOutDateTime);
				numberOfHoursAtWork += seconds.getSeconds();
			}
			return numberOfHoursAtWork / (3600);
		} catch (Exception e) {
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

}
