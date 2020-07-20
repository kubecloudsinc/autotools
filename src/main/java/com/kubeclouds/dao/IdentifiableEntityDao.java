
package com.kubeclouds.dao;

import java.util.List;

import com.kubeclouds.domain.appdb.IdentifiableEntity;
import org.springframework.dao.DataAccessException;

public interface IdentifiableEntityDao<E extends IdentifiableEntity> {
    public E getById(Long id) throws DataAccessException;

    public List<E> getAll() throws DataAccessException;

    public void save(E entity) throws DataAccessException;

    public void delete(E entity) throws DataAccessException;
    
    public void clearSessions();
}
