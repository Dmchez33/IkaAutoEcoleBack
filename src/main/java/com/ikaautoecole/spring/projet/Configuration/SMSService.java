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
        AUTH_TOKEN = "01dd36dcc3f979454b8d43bd4ef04b8c";
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

    public void sendSMSAcceptReserve(String phoneNumber, String prenom) {
        String mess = "Bonjour " + prenom +",\n"+
                "\n" +
                "Nous sommes heureux de vous informer que votre réservation a été acceptée. Vous pouvez maintenant passer au centre pour avoir le reste des informations.\n" +
                "\n" +
                "Merci pour votre confiance et à bientôt !\n" +
                "\n" +
                "Cordialement, IkaAutoEcole\ntél: +22361760827\nEmail: iddrdem83@gmail.com";
        try {
            Message message = Message
                    .creator(new PhoneNumber(phoneNumber), // to
                            new PhoneNumber(FROM_NUMBER), // from
                            mess)
                    .create();
            System.out.println("SMS sent successfully to " + phoneNumber);
        } catch (Exception e) {
            System.out.println("Error occurred while sending SMS: " + e.getMessage());
        }
    }

}

