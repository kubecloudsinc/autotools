
package com.kubeclouds.dao;

import com.kubeclouds.domain.appdb.Employee;
import org.springframework.dao.DataAccessException;

public interface EmployeeDao extends IdentifiableEntityDao<Employee> {
    public Employee getByEmail(String email) throws DataAccessException;
}
