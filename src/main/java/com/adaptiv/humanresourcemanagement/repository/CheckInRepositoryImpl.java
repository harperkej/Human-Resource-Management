package com.adaptiv.humanresourcemanagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.adaptiv.humanresourcemanagement.entity.CheckIn;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;

@Repository
public class CheckInRepositoryImpl implements CheckInRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private Logger logger = Logger.getLogger(CheckInRepository.class);

	public CheckIn createOne(CheckIn checkIn) throws RepositoryException {
		try {
			entityManager.persist(checkIn);
			return checkIn;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_SAVE_DB, e);
		}
	}

	public CheckIn findOneById(Long id) throws RepositoryException {
		try {
			CheckIn foundCheckIn = entityManager.find(CheckIn.class, id);
			if (foundCheckIn == null) {
				throw new Exception();
			}
			return foundCheckIn;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CheckIn> findCheckInsByEmployeeId(Long employeeId) throws RepositoryException {
		try {
			Query findCheckInsByEmployeeIdQuery = entityManager
					.createQuery("select checkin from CheckIn as checkin where checkin.employee.id = ?");
			findCheckInsByEmployeeIdQuery.setParameter(1, employeeId);
			return findCheckInsByEmployeeIdQuery.getResultList();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CheckIn> getAll() throws RepositoryException {
		try {
			Query getAllQuery = entityManager.createQuery("select checkin from CheckIn as checkin");
			return getAllQuery.getResultList();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}
}
