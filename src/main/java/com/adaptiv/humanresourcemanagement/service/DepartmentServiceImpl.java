package com.adaptiv.humanresourcemanagement.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adaptiv.humanresourcemanagement.entity.Department;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.ServiceException;
import com.adaptiv.humanresourcemanagement.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	private Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

	public Department createOne(Department department) throws ServiceException {
		try {
			return departmentRepository.createOne(department);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_SAVE_SERVICE, e);
		}
	}

	public Department updateOne(Department department) throws ServiceException {
		try {
			return departmentRepository.updateOne(department);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_UPDATE_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_UPDATE_SERVICE, e);
		}
	}

	public Department findOneById(Long id) throws ServiceException {
		try {
			return departmentRepository.findOneById(id);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

	public List<Department> getAll() throws ServiceException {
		try {
			return departmentRepository.getAll();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_SERVICE, e);
			throw new ServiceException(ExceptionType.COULD_NOT_FIND_SERVICE, e);
		}
	}

}
