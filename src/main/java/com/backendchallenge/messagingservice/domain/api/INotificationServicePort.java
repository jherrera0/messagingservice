package com.backendchallenge.messagingservice.domain.api;

public interface INotificationServicePort {
    void sendNotification(Long idOrder,String phone);
}
