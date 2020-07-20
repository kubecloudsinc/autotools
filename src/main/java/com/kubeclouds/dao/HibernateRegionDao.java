
package com.kubeclouds.dao;

import com.kubeclouds.domain.appdb.Region;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HibernateRegionDao extends AbstractHibernateDao<Region> implements RegionDao {

    @Transactional(readOnly = true, value="txManager")
    public List<Region> getAll() throws DataAccessException {
        return super.findAll("from Region order by id");
    }

    @Override
    @Transactional(readOnly = false, value="txManager")
    public void save(Region region) throws DataAccessException {
        super.save(region);
    }
}
