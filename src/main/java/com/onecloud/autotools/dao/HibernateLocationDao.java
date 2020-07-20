
package com.onecloud.autotools.dao;

import com.onecloud.autotools.domain.appdb.Location;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HibernateLocationDao extends AbstractHibernateDao<Location> implements LocationDao {

    @Transactional(readOnly = true, value="txManager")
    public List<Location> getAll() throws DataAccessException {
        return super.findAll("from Location order by id");
    }

    @Override
    @Transactional(readOnly = false, value="txManager")
    public void save(Location location) throws DataAccessException {
        super.save(location);
    }
}
