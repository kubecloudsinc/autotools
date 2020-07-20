
package com.onecloud.autotools.dao;

import com.onecloud.autotools.domain.appdb.Country;
import com.onecloud.autotools.domain.appdb.Region;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public class HibernateCountryDao implements CountryDao {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier(value="sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() throws HibernateException {
        return this.sessionFactory.getCurrentSession();
    }

    @Transactional(readOnly = false, value="txManager")
    public void save(Country entity) throws DataAccessException {
        this.logger.trace("Saving {}", entity);
        Session session = this.getSession();
        session.saveOrUpdate(entity);
        session.flush(); // force insert/update
        this.logger.debug("Saved {}", entity);
    }

    @Transactional(readOnly = false, value="txManager")
    public void delete(Country entity) throws DataAccessException {
        Session session = this.getSession();
        session.delete(entity);
        session.flush(); // force delete
        this.logger.debug("Deleted {}", entity);
    }

    @Transactional(readOnly = true, value="txManager")
    @SuppressWarnings("unchecked")
    public Country getById(String id) throws DataAccessException {
        Object result = this.getSession().get(Country.class, id);
        this.logger.debug("Got {} by id {}", result, id);
        return (Country) result;
    }

    @Transactional(readOnly = true, value="txManager")
    @SuppressWarnings("unchecked")
    public List<Country> getAll() {
        // this default implementation does not support any special sorting
        return this.getSession().createCriteria(Country.class).list();
    }

    @SuppressWarnings("unchecked")
    protected List<Country> findAll(String hqlQuery, Object... params) throws DataAccessException {
        Query query = this.getSession().createQuery(hqlQuery);
        this.initQueryParams(query, params);
        List<?> result = query.list();
        this.logger.debug("Found {} entities by query {}", +result.size(), hqlQuery);
        return (List<Country>) result;
    }

    @SuppressWarnings("unchecked")
    protected Country findOne(String hqlQuery, Object... params) throws DataAccessException {
        Query query = this.getSession().createQuery(hqlQuery);
        this.initQueryParams(query, params);
        Object result = query.uniqueResult();
        this.logger.debug("Found {} by query {}", result, hqlQuery);
        return (Country) result;
    }

    private void initQueryParams(Query query, Object... params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
        }
    }

    public void clearSessions(){
        if (getSession() != null) {
            getSession().clear(); // internal cache clear
        }
        Cache cache = sessionFactory.getCache();
        if (cache != null) {
            cache.evictAllRegions(); // Evict data from all query regions.
        }
        logger.info("CLEARED THE HIBERNATE SESSIONS");
    }


}
