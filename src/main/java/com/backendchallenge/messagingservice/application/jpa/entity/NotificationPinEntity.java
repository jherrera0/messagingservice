package com.backendchallenge.messagingservice.application.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(NotificationPinId.class)
public class NotificationPinEntity {
    @Id
    private String phoneNumber;

    @Id
    private String pin;
}
