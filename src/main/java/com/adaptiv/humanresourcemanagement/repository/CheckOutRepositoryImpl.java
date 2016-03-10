package com.adaptiv.humanresourcemanagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.adaptiv.humanresourcemanagement.entity.CheckOut;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;

@Repository
public class CheckOutRepositoryImpl implements CheckOutRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private Logger logger = Logger.getLogger(CheckOutRepository.class);

	public CheckOut createOne(CheckOut checkOut) throws RepositoryException {
		try {
			entityManager.persist(checkOut);
			return checkOut;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_SAVE_DB, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CheckOut> getAll() throws RepositoryException {
		try {
			Query findAllCheckOutsQuery = entityManager.createQuery("select checkout from CheckOut as checkout");
			return findAllCheckOutsQuery.getResultList();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}

	public CheckOut findCheckOutByCheckInId(Long checkInId) throws RepositoryException {
		try {
			Query findCheckOutByCheckInIdQuery = entityManager
					.createQuery("select checkout from CheckOut as checkout where checkout.checkIn.id = ?");
			findCheckOutByCheckInIdQuery.setParameter(1, checkInId);
			return (CheckOut) findCheckOutByCheckInIdQuery.getResultList().get(0);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CheckOut> findAllCheckOutsOfAnEmployeeByEmployeeId(Long employeeId) throws RepositoryException {
		try {
			Query findAllCheckOutsOfAnEmployeeByEmployeeIdQuery = entityManager
					.createQuery("select checkOut from CheckOut as checkOut where checkOut.checkIn.employee.id = ?");
			findAllCheckOutsOfAnEmployeeByEmployeeIdQuery.setParameter(1, employeeId);
			return findAllCheckOutsOfAnEmployeeByEmployeeIdQuery.getResultList();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}
}