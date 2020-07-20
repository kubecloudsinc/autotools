
package com.kubeclouds.dao;

import com.kubeclouds.domain.appdb.User;
import org.springframework.dao.DataAccessException;

public interface UserDao extends IdentifiableEntityDao<User> {
    public User getByEmail(String email) throws DataAccessException;
}
