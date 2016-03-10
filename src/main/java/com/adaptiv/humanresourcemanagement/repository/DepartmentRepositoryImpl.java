package com.adaptiv.humanresourcemanagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.adaptiv.humanresourcemanagement.entity.Department;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private Logger logger = Logger.getLogger(DepartmentRepositoryImpl.class);

	public Department findOneById(Long id) throws RepositoryException {
		try {
			Department foundDepartment = entityManager.find(Department.class, id);
			if (foundDepartment == null) {
				throw new Exception();
			}
			return foundDepartment;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}

	public Department createOne(Department department) throws RepositoryException {
		try {
			entityManager.persist(department);
			return department;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_SAVE_DB, e);
		}
	}

	public Department updateOne(Department department) throws RepositoryException {
		try {
			return entityManager.merge(department);
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_UPDATE_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_UPDATE_DB, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Department> getAll() throws RepositoryException {
		try {
			Query findAllDepartmentsQuery = entityManager.createQuery("select department from Department department");
			return (List<Department>) findAllDepartmentsQuery.getResultList();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}
}