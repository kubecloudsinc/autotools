
package com.kubeclouds.dao;

import com.kubeclouds.domain.appdb.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true, value="txManager")
    @Override
    public Employee getByFirstAndLastName(String firstName, String lastName) throws DataAccessException {
        return super.findOne
                ("from Employee where lower(firstName)=? and lower(lastName)=?"
                        , firstName.toLowerCase(),lastName.toLowerCase());
    }
}