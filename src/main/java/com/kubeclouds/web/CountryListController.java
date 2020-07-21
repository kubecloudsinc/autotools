
package com.kubeclouds.web;

import com.kubeclouds.dao.CountryDao;
import com.kubeclouds.domain.appdb.Country;
import com.kubeclouds.domain.appdb.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class CountryListController extends AbstractIdentifiableEntityController {

    protected CountryDao countryDao;

    // mask the super's Apache Commons Logging by SLF4J
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Required
    public void setCountryDao(CountryDao dao) {
        this.countryDao = dao;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        Region region = (Region) super.dao.getById(new Long(id));
        List<Country> result = countryDao.getAll();
        logger.debug("Got {} entities", result.size());
        ModelAndView mav = new ModelAndView(super.getViewName());
        mav.addObject(result);
        mav.addObject(region);
        return mav;
    }
}