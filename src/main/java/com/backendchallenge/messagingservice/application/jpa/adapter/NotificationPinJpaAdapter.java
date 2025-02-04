package com.backendchallenge.messagingservice.application.jpa.adapter;

import com.backendchallenge.messagingservice.application.jpa.entity.NotificationPinEntity;
import com.backendchallenge.messagingservice.application.jpa.repository.INotificationPinRepository;
import com.backendchallenge.messagingservice.domain.spi.INotificationPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class NotificationPinJpaAdapter implements INotificationPersistencePort {
    private final INotificationPinRepository notificationPinRepository;

    @Override
    public void savePin(String phoneNumber, String pin) {
        NotificationPinEntity notificationPinEntity = new NotificationPinEntity();
        notificationPinEntity.setPhoneNumber(phoneNumber);
        notificationPinEntity.setPin(pin);
        notificationPinRepository.save(notificationPinEntity);
    }

    @Override
    public void updatePin(String phoneNumber, String pin) {
        notificationPinRepository.findByPhoneNumber(phoneNumber).ifPresent(notificationPinRepository::delete);
        savePin(phoneNumber, pin);
    }

    @Override
    public Optional<String> findPinByPhoneNumber(String phoneNumber) {
        return notificationPinRepository.findByPhoneNumber(phoneNumber)
                .map(NotificationPinEntity::getPin);
    }
}
