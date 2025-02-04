package com.backendchallenge.messagingservice.domain.spi;

public interface IMessagePersistencePort {
    void sendMessage(String phone,String message);
}
