
package com.onecloud.autotools.dao;

import com.onecloud.autotools.domain.appdb.Employee;
import com.onecloud.autotools.domain.appdb.User;
import org.springframework.dao.DataAccessException;

public interface EmployeeDao extends IdentifiableEntityDao<Employee> {
    public Employee getByEmail(String email) throws DataAccessException;
}
