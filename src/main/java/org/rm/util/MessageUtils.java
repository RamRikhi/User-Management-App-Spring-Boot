package org.rm.util;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
    Logger logger = LoggerFactory.getLogger(MessageUtils.class);
    private static final String AUTH_TOKEN = "256799e621a5b30eaef0c3691b974e2d";
    private static final String ACCOUNT_SID = "AC65e53b7a2ebb523ecc44c200bbe38c9c";
    private static final String OWNER_PHONE = "+14842092841";
    public boolean sendSMS(String userPhone,String pazzword){
        logger.info("Enmaakjflafjalhfhalh");
        boolean isSent = false;
        String messageBody = "Log in using this password::"+pazzword;
        String userMobile = "+91".concat(userPhone);
        try{
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber(userMobile),new PhoneNumber("+919040652072"),messageBody).create();
            String sid = message.getSid();
            logger.info(sid);
            isSent = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSent;
    }
}
