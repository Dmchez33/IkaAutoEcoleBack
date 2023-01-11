package com.ikaautoecole.spring.projet.Configuration;

import com.messagebird.MessageBirdService;
import com.messagebird.MessageBirdServiceImpl;
import com.messagebird.objects.Message;
import org.springframework.stereotype.Service;
import com.messagebird.MessageBirdClient;
import com.messagebird.exceptions.GeneralException;
import com.messagebird.exceptions.NotFoundException;
import com.messagebird.exceptions.UnauthorizedException;
import com.messagebird.objects.MessageResponse;

@Service
public class SMSService {
    private final MessageBirdClient messageBirdClient;

    public SMSService() {
        MessageBirdService messageBirdService = new MessageBirdServiceImpl("yYkxXq1HjjPf3QYhsqRQvMxJn");
        messageBirdClient = new MessageBirdClient(messageBirdService);
    }

    public void sendSMS(String phoneNumber, String messageContent) {
        Message message = new Message("Idrissa DEMBELE", messageContent, String.valueOf(new String[] {phoneNumber}));
        try {
            messageBirdClient.sendMessage(message);
            System.out.println("SMS sent successfully to " + phoneNumber);
        } catch (Exception e) {
            System.out.println("Error occurred while sending SMS: " + e.getMessage());
        }
    }
}

