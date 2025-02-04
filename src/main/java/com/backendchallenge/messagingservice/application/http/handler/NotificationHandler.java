package com.backendchallenge.messagingservice.application.http.handler;

import com.backendchallenge.messagingservice.application.http.dto.OrderReadyRequest;
import com.backendchallenge.messagingservice.application.http.handler.interfaces.INotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationHandler implements INotificationHandler {
    private final INotificationServicePort notificationServicePort;

    @Override
    public void sendNotification(OrderReadyRequest orderReadyRequest) {
        notificationServicePort.sendNotification(orderReadyRequest.getOrderId(), orderReadyRequest.getPhone());
    }
}
