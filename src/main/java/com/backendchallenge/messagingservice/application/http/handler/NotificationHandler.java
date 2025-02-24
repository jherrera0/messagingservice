package com.backendchallenge.messagingservice.application.http.handler;

import com.backendchallenge.messagingservice.application.http.dto.OrderReadyRequest;
import com.backendchallenge.messagingservice.application.http.handler.interfaces.INotificationHandler;
import com.backendchallenge.messagingservice.domain.api.INotificationServicePort;
import com.backendchallenge.messagingservice.domain.until.TokenHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationHandler implements INotificationHandler {
    private final INotificationServicePort notificationServicePort;

    @Override
    public void sendNotification(OrderReadyRequest orderReadyRequest) {
        TokenHolder.getToken();
        notificationServicePort.sendNotification(orderReadyRequest.getOrderId(), orderReadyRequest.getPhone());
    }

    @Override
    public boolean existPinByPhoneNumber(String phone) {
        return notificationServicePort.existPinByPhoneNumber(phone);
    }

    @Override
    public String findPinByPhoneNumber(String phone) {
        return notificationServicePort.findPinByPhoneNumber(phone);
    }
}
