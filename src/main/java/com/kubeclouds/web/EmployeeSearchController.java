
package com.kubeclouds.web;

import com.kubeclouds.dao.EmployeeDao;
import com.kubeclouds.domain.appdb.Country;
import com.kubeclouds.domain.appdb.Employee;
import com.kubeclouds.domain.appdb.Region;
import com.kubeclouds.domain.appdb.User;
import com.kubeclouds.semantic.test.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/employee_search.html")
public class EmployeeSearchController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeSearchController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("employeeDTO")
                                @Valid EmployeeDTO employeeDTO,
                                BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            Employee employee =
                    employeeDao.getByFirstAndLastName(employeeDTO.getFirstName(),employeeDTO.getLastName());
            employeeDTO.setSearchComplete(true);
            if(employee!=null) {
                employeeDTO.setFoundResult(true);
                employeeDTO.transfromFromObject(employee);
            }else{
//                employeeDTO.setSearchComplete(true);
                employeeDTO.setFoundResult(false);
            }

            status.setComplete();
        }
        return "employeeSearch";
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView handleRequestInternal() throws Exception {
        ModelAndView mav = new ModelAndView("employeeSearch")
                .addObject(new EmployeeDTO());
        return mav;
    }
}