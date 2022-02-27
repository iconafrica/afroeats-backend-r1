package com.iconsoft.afroeats.GestionNotification.GestionSms.Configuration;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializ {

    @Autowired
    TwilioInitializ(){
        Twilio.init(
                TwilioParams.ACCOUNT_SID,
                TwilioParams.AUTH_TOKEN
        );
    }
}
