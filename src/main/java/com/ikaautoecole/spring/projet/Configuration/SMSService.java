package com.ikaautoecole.spring.projet.Configuration;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SMSService {
    private final String ACCOUNT_SID;
    private final String AUTH_TOKEN;
    private final String FROM_NUMBER;

    public SMSService() {
        ACCOUNT_SID = "AC059e81ff4a26001119e3b19df555edea";
        AUTH_TOKEN = "df49fe2cfb1cad831029f56b366daa39";
        FROM_NUMBER = "+17756307308";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSMS(String phoneNumber, String messageContent) {
        try {
            Message message = Message
                    .creator(new PhoneNumber(phoneNumber), // to
                            new PhoneNumber(FROM_NUMBER), // from
                            messageContent)
                    .create();
            System.out.println("SMS sent successfully to " + phoneNumber);
        } catch (Exception e) {
            System.out.println("Error occurred while sending SMS: " + e.getMessage());
        }
    }
}

