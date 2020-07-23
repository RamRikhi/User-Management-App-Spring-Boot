package org.rm.controller;

import org.rm.domain.UnlockAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UnlockAccountController {

    /**
     *This controller method handle coming request for unlock account
     */
    @RequestMapping(value = "/unlockAcc")
    public String unlockAccount(@RequestParam("email") String email, Model model){
        UnlockAccount unlockAccount = new UnlockAccount();
        model.addAttribute("email",email);
        return "unlockAccount";
    }

    @PostMapping("/unlockAccDetails")
    public String handleUnlockAccBtn(@ModelAttribute("unlockAccount") UnlockAccount unlockAccount,Model model){
        return "";
    }
}
