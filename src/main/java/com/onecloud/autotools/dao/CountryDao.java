
package com.onecloud.autotools.dao;

import com.onecloud.autotools.domain.appdb.Country;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CountryDao{

    public Country getById(String country_id) throws DataAccessException;

    public List<Country> getAll() throws DataAccessException;

    public void save(Country entity) throws DataAccessException;

    public void delete(Country entity) throws DataAccessException;

    public void clearSessions();

}
