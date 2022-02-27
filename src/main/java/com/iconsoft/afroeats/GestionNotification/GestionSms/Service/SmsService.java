package com.iconsoft.afroeats.GestionNotification.GestionSms.Service;

import com.iconsoft.afroeats.GestionNotification.GestionSms.Configuration.TwilioParams;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements SmsSender{

    @Override
    public void sendSms(SmsRequest smsRequest) {
        if(isPhoneNumberValid(smsRequest.getPhoneNumber())){
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(TwilioParams.TRIAL_NUMBER);
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
        } else {
            throw new IllegalArgumentException("Le numéro [" + smsRequest.getPhoneNumber() +"] n'est pas valide.");
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //Implémentation de la méthode de vérification d'un numéro
        System.out.println(phoneNumber);

        return true;
    }

    /*@Bean
    void TestSMS(){
        SmsRequest smsRequest = new SmsRequest("+237652041103", "Hello world !!!");
        sendSms(smsRequest);
    }*/
}