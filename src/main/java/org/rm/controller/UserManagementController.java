package org.rm.controller;

import org.rm.domain.ForgotPazzword;
import org.rm.domain.LoginUser;
import org.rm.domain.UserAccount;
import org.rm.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserManagementController {

    /**
     * Injecting service layer into controller
     */
    @Autowired
    private UserManagementService userService;

    /**
     * this method render the login form into home page of the our application
     * @param model
     * @return
     */
    @RequestMapping(value = {"/","/login"})
    public String homePage(Model model){
        LoginUser login = new LoginUser();
        model.addAttribute("login",login);
        return "index";
    }

    /**
     * This method will handle login submit btn and give the control to service layer
     * @param loginUser
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    public String loginFormHandler(@ModelAttribute("login") LoginUser loginUser,Model model){
        String isUserStatus = userService.isUserExist(loginUser);
        if(isUserStatus.equals("VALID")){
            return "dashboard";
        }else if (isUserStatus.equals("LOCKED")){
            model.addAttribute("errMsg", "Your Account is locked please contact to admin.<br><br><a href='login'>Go Back</a>");
            return "errorPage";
        }
        model.addAttribute("errMsg","User doesn't exist <br><a href='signUpForm'>click here</a> to sign up.<br><br><a href='login'>Go Back</a>");
        return "errorPage";
    }

    /**
     *loadForm() will interact with service layer and it will launch the registration form
     */
    @RequestMapping(value = "/signUpForm",method = RequestMethod.GET)
    public String loadForm(Model model){
        UserAccount user = new UserAccount();
        Map<Integer,String> countryMap = userService.getAllCountries();
        model.addAttribute("countries",countryMap);
        model.addAttribute("userAcc",user);

        return "userRegister";
    }

    /**
     * This method will retrieve sign up form data and transfer control to service layer
     * @param userAccount
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveUserDetails",method = RequestMethod.POST)
    public String handleSubmitButton(@ModelAttribute("userAcc") UserAccount userAccount, Model model){
        boolean isSaved = userService.saveUser(userAccount);
        if(isSaved){
            return "redirect:/registered";
        }
        model.addAttribute("errMsg","Failed to registered please try again...We are regret for the problem...");
        return "errorPage";
    }

    /**
     * This method will handle redirect url from sign up page of successfully user registered
     * @param model
     * @return
     */
    @RequestMapping(value = "/registered")
    public String successSavedUser(Model model){
        model.addAttribute("succMsg","Thank you for registration!!!<br><br>Please check your email and verify your account.");
        return "success";
    }

    /**
     * This method will fetch the states based on country id when ajax event raise
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getStates")
    @ResponseBody
    public Map<Integer,String> getStatesByCountryId(@RequestParam("cid") Integer cid){
        return userService.getStatesByCountryId(cid);
    }

    /**
     * this method will fetch the cities based on state Id when ajax event raise
     * @param sid
     * @return
     */
    @RequestMapping(value = "/getCities")
    @ResponseBody
    public Map<Integer,String> getCitiesByStateId(@RequestParam("sid") Integer sid){
        return userService.getCitiesByStateId(sid);
    }

    /**
     * This method will check email is exist or not in the database when ajax call will happen
     * @param email
     * @return
     */
    @RequestMapping(value = "/validateEmail")
    @ResponseBody
    public String validateEmail(@RequestParam("email") String email){
        return userService.findByEmail(email);
    }

    /**
     * This method will lauch forgot password form
     * @param model
     * @return
     */
    @RequestMapping(value = "/forgotPassword")
    public String forgotPazzword(Model model){
        ForgotPazzword forgot = new ForgotPazzword();
        model.addAttribute("forgotPazzword",forgot);
        return "forgotPassword";
    }

    @RequestMapping(value = "/recoverPassword",method = RequestMethod.POST)
    public String forgotPazzwordHandler(@ModelAttribute("forgotPazzword") ForgotPazzword forgot,Model model){
        String recoverStatus = userService.passwordRecover(forgot.getEmail());
        if (recoverStatus.equals("VALID")){
            model.addAttribute("msg","Password has been sent to your registered mobile number "+ recoverStatus);
            return "forgotPassword";
        }else if (recoverStatus.equals("LOCKED")){
            model.addAttribute("msg","Your account is locked please contact to admin "+ recoverStatus);
            return "forgotPassword";
        }
        model.addAttribute("msg","Password has been sent to your registered mobile number "+recoverStatus);
        return "forgotPassword";
    }
}
