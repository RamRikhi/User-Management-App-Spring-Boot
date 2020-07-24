package org.rm.controller;

import org.rm.contants.UserAccountStatus;
import org.rm.domain.UnlockAccount;
import org.rm.domain.UserAccount;
import org.rm.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UnlockAccountController {

    @Autowired
    private UserManagementService userService;

    /**
     *This controller method handle coming request for unlock account
     */
    @RequestMapping(value = "/unlockAcc")
    public String unlockAccountForm(@RequestParam("email") String email, Model model){
        UnlockAccount unlockUserAcc = new UnlockAccount();
        unlockUserAcc.setEmail(email);
        model.addAttribute("unlockUserAcc",unlockUserAcc);

        return "unlockAccount";
    }

    /**
     * This method will handled unlocked account request and navigated to service layer
     * @param unlockUserAcc
     * @param model
     * @return
     */
    @PostMapping("/unlockAccDetails")
    public String handleUnlockAccSubmit(@ModelAttribute("unlockUserAcc") UnlockAccount unlockUserAcc,Model model){
        UserAccount userAccount = userService.isTempPwdValid(unlockUserAcc.getEmail(),unlockUserAcc.getTempPwd());
        if (userAccount != null){
            userAccount.setAccountStatus(String.valueOf(UserAccountStatus.UNLOCKED));
            userAccount.setUserPwd(unlockUserAcc.getNewPwd());
            boolean isUpdated = userService.updateUserAccount(userAccount);
            if(isUpdated){
                model.addAttribute("succMsg","Your account is successfully unlocked");
                return "success";
            }
        }
        model.addAttribute("errMsg","Failed to unlocked your account");
        return "errorPage";
    }
}
