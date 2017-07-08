package com.codeup.svcs;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("twilioSvc")
public class TwilioSvc {
    @Value("${twilio-account-sid}")
    private final String accountSID = "AC4ccab2cf6031270b5583c283dc9364c1"; // Your Acct SID from www.twilio.com/user/account;

    @Value("${twilio-auth-token}")
    private String authToken = "9f02b78fba66e39220e0d7523b087ebd"; // Your Auth Token from www.twilio.com/user/account;

    public void sendMessage(String toPhone, String fromPhone, String text){
        Twilio.init(accountSID, authToken);

        Message message = Message.creator(
                new PhoneNumber(toPhone),
                new PhoneNumber(fromPhone),
                text).create();
        System.out.println(message.getStatus());
    }
}
