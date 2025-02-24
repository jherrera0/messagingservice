package com.backendchallenge.messagingservice.domain.usecase;

import com.backendchallenge.messagingservice.domain.api.INotificationServicePort;
import com.backendchallenge.messagingservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.messagingservice.domain.spi.IMessagePersistencePort;
import com.backendchallenge.messagingservice.domain.spi.INotificationPersistencePort;
import com.backendchallenge.messagingservice.domain.until.ConstJwt;
import com.backendchallenge.messagingservice.domain.until.TokenHolder;

import java.util.Random;

import static com.backendchallenge.messagingservice.domain.until.ConstNotification.*;

public class NotificationCase implements INotificationServicePort {
    private final Random random;
    private final IJwtPersistencePort jwtPersistencePort;
    private final INotificationPersistencePort notificationPersistencePort;
    private final IMessagePersistencePort messagePersistencePort;

    public NotificationCase(IJwtPersistencePort jwtPersistencePort,
                            INotificationPersistencePort notificationPersistencePort,
                            IMessagePersistencePort messagePersistencePort) {
        this.jwtPersistencePort = jwtPersistencePort;
        this.notificationPersistencePort = notificationPersistencePort;
        this.messagePersistencePort = messagePersistencePort;
        this.random = new Random();
    }
    @Override
    public void sendNotification(Long idOrder, String phone) {
        String token = TokenHolder.getTokenValue().substring(ConstJwt.LINESTRING_INDEX);
        Long userId = jwtPersistencePort.getUserId(token);
        String pin = generatePin();
        String messageWithPin =  MESSAGE_NOTIFICATION+ConstJwt.SPLITERSTRING+userId+ConstJwt.SPLITERSTRING+
                MESSAGE_NOTIFICATION_2+ConstJwt.SPLITERSTRING+pin;

        if(notificationPersistencePort.findPinByPhoneNumber(phone).isPresent()){
            notificationPersistencePort.updatePin(phone, pin);
        }
        notificationPersistencePort.savePin(phone, pin);
        messagePersistencePort.sendMessage(phone, messageWithPin);

    }

    @Override
    public Boolean existPinByPhoneNumber(String phone) {
        return notificationPersistencePort.findPinByPhoneNumber(phone).isPresent();
    }

    @Override
    public String findPinByPhoneNumber(String phone) {
        return notificationPersistencePort.getPinByPhoneNumber(phone);
    }

    private String generatePin() {
        return String.format(PIN_FORMAT, random.nextInt(RANDOM_PARAMETER));
    }
}
