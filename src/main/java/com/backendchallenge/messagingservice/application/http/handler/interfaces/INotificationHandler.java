package com.backendchallenge.messagingservice.application.http.handler.interfaces;

import com.backendchallenge.messagingservice.application.http.dto.OrderReadyRequest;

public interface INotificationHandler {
    void sendNotification(OrderReadyRequest orderReadyRequest);
}
