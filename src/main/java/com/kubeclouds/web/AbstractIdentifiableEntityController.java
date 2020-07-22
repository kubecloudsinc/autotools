
package com.kubeclouds.web;

import com.kubeclouds.domain.appdb.IdentifiableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.kubeclouds.dao.IdentifiableEntityDao;

public abstract class AbstractIdentifiableEntityController extends ParameterizableViewController {

    protected IdentifiableEntityDao<IdentifiableEntity> dao;

    // mask the super's Apache Commons Logging by SLF4J
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Required
    public void setDao(IdentifiableEntityDao<IdentifiableEntity> dao) {
        this.dao = dao;
    }
}
