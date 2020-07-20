
package com.onecloud.autotools.web;

import com.onecloud.autotools.dao.CountryDao;
import com.onecloud.autotools.dao.LocationDao;
import com.onecloud.autotools.domain.appdb.Country;
import com.onecloud.autotools.domain.appdb.Department;
import com.onecloud.autotools.domain.appdb.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class DepartmentListController extends ParameterizableViewController {

    protected LocationDao dao;

    // mask the super's Apache Commons Logging by SLF4J
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Required
    public void setDao(LocationDao dao) {
        this.dao = dao;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        Location location = this.dao.getById(new Long(id));
        Set<Department> result = location.getDepartments();
        logger.debug("Got {} entities", result.size());
        ModelAndView mav = new ModelAndView(super.getViewName());
        mav.addObject(result);
        mav.addObject(location);
        return mav;
    }
}
