package com.backendchallenge.messagingservice.application.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationPinId implements Serializable {
    private String phoneNumber;
    private String pin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationPinId that)) return false;
        return phoneNumber.equals(that.phoneNumber) && pin.equals(that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, pin);
    }
}
