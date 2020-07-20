
package com.onecloud.autotools.web;

import com.onecloud.autotools.dao.RegionDao;
import com.onecloud.autotools.domain.appdb.Country;
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

public class CountryListController extends AbstractIdentifiableEntityController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        Region region = (Region) super.dao.getById(new Long(id));
        Set<Country> result = region.getCountries();
        logger.debug("Got {} entities", result.size());
        ModelAndView mav = new ModelAndView(super.getViewName());
        mav.addObject(result);
        mav.addObject(region);
        return mav;
    }
}