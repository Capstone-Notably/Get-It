package com.codeup.svcs;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("twilioSvc")
public class TwilioSvc {
    @Value("${twilio-account-sid}")
    private String accountSID;

    @Value("${twilio-auth-token}")
    private String authToken;

    public void sendMessage(String toPhone, String text){
        String fromPhone = "+18304200837";
        Twilio.init(accountSID, authToken);

        Message message = Message.creator(
                new PhoneNumber(toPhone),
                new PhoneNumber(fromPhone),
                text).create();
        System.out.println(message.getStatus());
    }
}
