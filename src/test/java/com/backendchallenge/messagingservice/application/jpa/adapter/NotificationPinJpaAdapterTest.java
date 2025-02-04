package com.backendchallenge.messagingservice.application.jpa.adapter;

import com.backendchallenge.messagingservice.application.jpa.entity.NotificationPinEntity;
import com.backendchallenge.messagingservice.application.jpa.repository.INotificationPinRepository;
import com.backendchallenge.messagingservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class NotificationPinJpaAdapterTest {

    @Mock
    private INotificationPinRepository notificationPinRepository;

    @InjectMocks
    private NotificationPinJpaAdapter notificationPinJpaAdapter;

    AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }
    @Test
    void savePin_savesPinSuccessfully() {
        String phoneNumber = ConstTest.PHONE;
        String pin = ConstTest.PIN_TEST;

        notificationPinJpaAdapter.savePin(phoneNumber, pin);

        verify(notificationPinRepository, times(ConstTest.ONE)).save(any(NotificationPinEntity.class));
    }
    @Test

    void updatePin_updatesPinSuccessfully() {
        String phoneNumber = ConstTest.PHONE;
        String pin = ConstTest.PIN_TEST;

        when(notificationPinRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(new NotificationPinEntity()));

        notificationPinJpaAdapter.updatePin(phoneNumber, pin);

        verify(notificationPinRepository, times(ConstTest.ONE)).delete(any(NotificationPinEntity.class));
        verify(notificationPinRepository, times(ConstTest.ONE)).save(any(NotificationPinEntity.class));
    }
    @Test
    void findPinByPhoneNumber_returnsPinIfExists() {
        String phoneNumber = ConstTest.PHONE;
        String pin = ConstTest.PIN_TEST;
        NotificationPinEntity entity = new NotificationPinEntity();
        entity.setPhoneNumber(phoneNumber);
        entity.setPin(pin);

        when(notificationPinRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(entity));

        Optional<String> result = notificationPinJpaAdapter.findPinByPhoneNumber(phoneNumber);

        assertTrue(result.isPresent());
        assertEquals(pin, result.get());
    }

    @Test
    void findPinByPhoneNumber_returnsEmptyIfNotExists() {
        String phoneNumber = ConstTest.PHONE;

        when(notificationPinRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.empty());

        Optional<String> result = notificationPinJpaAdapter.findPinByPhoneNumber(phoneNumber);

        assertTrue(result.isEmpty());
    }
}