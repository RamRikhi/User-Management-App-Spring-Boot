package org.rm.controller;

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
     *loadForm() will interact with service layer and it will launch the registration form
     */
    @RequestMapping(value = {"/","/loadForm"},method = RequestMethod.GET)
    public String loadForm(Model model){
        UserAccount user = new UserAccount();
        Map<Integer,String> countryMap = userService.getAllCountries();
        model.addAttribute("countries",countryMap);
        model.addAttribute("userAcc",user);

        return "userRegister";
    }

    @RequestMapping(value = "/saveUserDetails",method = RequestMethod.POST)
    public String handleSubmitButton(@ModelAttribute("userAcc") UserAccount userAccount, Model model){
        boolean isSaved = userService.saveUser(userAccount);
        if(isSaved){
            return "redirect:/registered";
        }
        return "errorPage";
    }

    @RequestMapping(value = "/registered")
    public String successSavedUser(Model model){
        model.addAttribute("succMsg","Thank you for registration!!!<br><br>Please check your email and verify your account.");
        return "successRegistered";
    }

    @RequestMapping(value = "/getStates")
    @ResponseBody
    public  Map<Integer,String> getStatesBhCountry(@RequestParam("countryId") Integer cid){
        return userService.getStatesByCountryId(cid);
    }

}
