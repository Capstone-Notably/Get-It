package com.codeup.svcs;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("twilioSvc")
public class TwilioSvc {
    @Value("${twilio-account-sid}")
    private final String accountSID = "AC9d9cc9c5c456ad89aee36afb3b70c373"; // Your Acct SID from www.twilio.com/user/account;

    @Value("${twilio-auth-token}")
    private String authToken = "e96f39771a5593a39c0dd586143a8549"; // Your Auth Token from www.twilio.com/user/account;

    public void sendMessage(String toPhone, String fromPhone, String text){
        Twilio.init(accountSID, authToken);

        Message message = Message.creator(
                new PhoneNumber(toPhone),
                new PhoneNumber(fromPhone),
                text).create();
        System.out.println(message.getStatus());
    }
}
