package org.rm.util;

import org.rm.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class EmailUtils {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * This method will take UserAccount details and send email based on passed details
     * @param userAccount
     * @return
     */
    public boolean sendUserAccUnlockEmail(UserAccount userAccount){
        boolean isSent = false;
        MimeMessage mimeMessage = null;
        MimeMessageHelper helper = null;
        try {
            /**
             * MimeMessage Object creation
             */
            mimeMessage = mailSender.createMimeMessage();
            /**
             * MimeHelper Object creation
             */
            helper = new MimeMessageHelper(mimeMessage);
            /**
             * setting the value into the email sender
             */
            helper.setTo(userAccount.getUserEmail());
            helper.setSubject("Verify Your Email");
            helper.setText(getUnlockAccEmailBody(userAccount),true);

            /**
             *Here Mail will send to user
             */
            mailSender.send(mimeMessage);
            isSent = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSent;
    }

    private String getUnlockAccEmailBody(UserAccount userAccount) throws IOException {
        StringBuffer sb = new StringBuffer("");

        FileReader reader = new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();

        while (line != null){
            sb.append(line);
            line = br.readLine();
        }
        br.close();

        String mailBody = sb.toString();
        mailBody = mailBody.replace("{FNAME}",userAccount.getFirstName());
        mailBody = mailBody.replace("{LNAME}",userAccount.getLastName());
        mailBody = mailBody.replace("{TEMP-PWD}",userAccount.getUserPwd());
        mailBody = mailBody.replace("{EMAIL}",userAccount.getUserEmail());
        return mailBody;
    }
}
