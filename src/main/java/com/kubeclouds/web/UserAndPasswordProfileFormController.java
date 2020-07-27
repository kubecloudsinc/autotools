
package com.kubeclouds.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.kubeclouds.domain.appdb.User;
import com.kubeclouds.service.UserContextService;
import com.kubeclouds.service.UserStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping({"/user_profile.html", "/user_register.html"})
@SessionAttributes("userAndPassword")
public class UserAndPasswordProfileFormController {
    private static final Logger logger = LoggerFactory
            .getLogger(UserAndPasswordProfileFormController.class);

    private final UserContextService userContextService;

    private final UserStoreService userStoreService;

    @Autowired
    public UserAndPasswordProfileFormController(UserContextService userContextService,
            UserStoreService userStoreService) {
        this.userContextService = userContextService;
        this.userStoreService = userStoreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ModelAttribute("userAndPassword")
    public ModelAndView setupForm() {
        User user = this.userContextService.getUserFromContext();
        if (user == null) {
            logger.debug("Setting up form for new user");
            user = new User();
        } else {
            logger.debug("Setting up form for {}", user);
        }
        return new ModelAndView("userForm").addObject(new UserAndPassword(user)).addObject("profile", Boolean.TRUE);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("user.enabled", "user.admin", "user.passwordDigest");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("userAndPassword") @Valid UserAndPassword userAndPassword,
                BindingResult result, @RequestParam(value = "success", required = false) Boolean success,SessionStatus status,
                                HttpServletRequest request) {
        if(success!=null){
            logger.debug("the success is "+Boolean.valueOf(success).toString());
            success=false;
        }else{
            logger.debug("the success is null");
        }
        if (!result.hasErrors()) {
            try {
                User user = userAndPassword.getUser();
                logger.debug("Processing submit for {}", user);
                String password = null;
                if (userAndPassword.isPasswordVerified()) {
                    password = userAndPassword.getPassword();
                }
                logger.debug("Storing {}", user);
                this.userStoreService.store(user, password);
                status.setComplete();
                logger.debug("Logging in {}", user);
                this.userContextService.addUserToContext(user, password);
                logger.debug("Done {}", user);
                return "redirect:user_profile.html?success=true";
            } catch (DataIntegrityViolationException e) {
                result.rejectValue("user.email", "DuplicateEmailFailure");
            } catch (ConcurrencyFailureException e) {
                result.reject("ConcurrentModificatonFailure");
            }
        }else{
            List<FieldError> errors= result.getFieldErrors();
            logger.debug("The errors: "+errors.size());
            for (FieldError error : errors){
                logger.debug(error.toString());
            }
           request.removeAttribute("success");
        }
        return "userForm";
    }
}
