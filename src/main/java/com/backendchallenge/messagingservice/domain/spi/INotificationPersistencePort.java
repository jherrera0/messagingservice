package com.backendchallenge.messagingservice.domain.spi;

import java.util.Optional;

public interface INotificationPersistencePort {
    void savePin(String phoneNumber, String pin);
    void updatePin(String phoneNumber, String pin);
    Optional<String> findPinByPhoneNumber(String phoneNumber);
    String getPinByPhoneNumber(String phoneNumber);
}
