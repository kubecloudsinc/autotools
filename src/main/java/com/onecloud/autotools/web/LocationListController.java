
package com.onecloud.autotools.web;

import com.onecloud.autotools.dao.CountryDao;
import com.onecloud.autotools.dao.IdentifiableEntityDao;
import com.onecloud.autotools.domain.appdb.Country;
import com.onecloud.autotools.domain.appdb.IdentifiableEntity;
import com.onecloud.autotools.domain.appdb.Location;
import com.onecloud.autotools.domain.appdb.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class LocationListController extends ParameterizableViewController {

    protected CountryDao dao;

    // mask the super's Apache Commons Logging by SLF4J
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Required
    public void setDao(CountryDao dao) {
        this.dao = dao;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String id = ServletRequestUtils.getRequiredStringParameter(request, "id");
        Country country = this.dao.getById(id);
        Set<Location> result = country.getLocations();
        logger.debug("Got {} entities", result.size());
        ModelAndView mav = new ModelAndView(super.getViewName());
        mav.addObject(result);
        mav.addObject(country);
        return mav;
    }
}
