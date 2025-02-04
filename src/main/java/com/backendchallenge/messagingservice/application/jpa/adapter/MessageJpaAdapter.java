package com.backendchallenge.messagingservice.application.jpa.adapter;

import com.backendchallenge.messagingservice.domain.spi.IMessagePersistencePort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class MessageJpaAdapter implements IMessagePersistencePort {
    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendMessage(String phone, String message) {
        Message.creator(
                new PhoneNumber(phone),
                new PhoneNumber(twilioPhoneNumber),
                message
        ).create();
    }
}
