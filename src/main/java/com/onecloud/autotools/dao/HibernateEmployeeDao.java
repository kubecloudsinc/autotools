
package com.onecloud.autotools.dao;

import com.onecloud.autotools.domain.appdb.Employee;
import com.onecloud.autotools.domain.appdb.User;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class HibernateEmployeeDao extends AbstractHibernateDao<Employee> implements EmployeeDao {

    @Transactional(readOnly = true, value="txManager")
    public List<Employee> getAll() throws DataAccessException {
        return super.findAll("from Employee order by firstName, lastName");
    }

    @Transactional(readOnly = true, value="txManager")
    @Override
    public Employee getByEmail(String email) throws DataAccessException {
        return super.findOne("from Employee where email=?", email);
    }
}