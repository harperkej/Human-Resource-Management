package com.adaptiv.humanresourcemanagement.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.adaptiv.humanresourcemanagement.entity.Employee;
import com.adaptiv.humanresourcemanagement.exception.ExceptionType;
import com.adaptiv.humanresourcemanagement.exception.RepositoryException;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private Logger logger = Logger.getLogger(EmployeeRepository.class);

	public Employee createOne(Employee employee) throws RepositoryException {
		try {
			entityManager.persist(employee);
			return employee;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_SAVE_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_SAVE_DB, e);
		}
	}

	public Employee findOneById(Long id) throws RepositoryException {
		try {
			Employee foundUser = entityManager.find(Employee.class, id);
			if (foundUser == null) {
				throw new Exception();
			}
			return foundUser;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}

	public Employee updateOne(Employee employee) throws RepositoryException {
		try {
			Employee updatedUser = entityManager.merge(employee);
			return updatedUser;
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_UPDATE_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_UPDATE_DB, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAll() throws RepositoryException {
		try {
			Query findAllEmployeesQuery = entityManager.createQuery("select employee from Employee employee");
			return (List<Employee>) findAllEmployeesQuery.getResultList();
		} catch (Exception e) {
			logger.error(ExceptionType.COULD_NOT_FIND_DB, e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findByDepartmentId(Long departmentId) throws RepositoryException {
		try {
			Query findAllByDepartmentQuery = entityManager
					.createQuery("select employee from Employee as employee where employee.department.id = ?");
			findAllByDepartmentQuery.setParameter(1, departmentId);
			return findAllByDepartmentQuery.getResultList();
		} catch (Exception e) {
			logger.error(e);
			throw new RepositoryException(ExceptionType.COULD_NOT_FIND_DB, e);
		}
	}
}