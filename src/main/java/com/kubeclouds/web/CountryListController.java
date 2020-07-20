
package com.kubeclouds.web;

import com.kubeclouds.domain.appdb.Country;
import com.kubeclouds.domain.appdb.Region;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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