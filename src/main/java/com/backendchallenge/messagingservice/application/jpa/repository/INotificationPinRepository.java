package com.backendchallenge.messagingservice.application.jpa.repository;

import com.backendchallenge.messagingservice.application.jpa.entity.NotificationPinEntity;
import com.backendchallenge.messagingservice.application.jpa.entity.NotificationPinId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface INotificationPinRepository extends JpaRepository<NotificationPinEntity, NotificationPinId> {
    Optional<NotificationPinEntity> findByPhoneNumber(String phoneNumber);
}
