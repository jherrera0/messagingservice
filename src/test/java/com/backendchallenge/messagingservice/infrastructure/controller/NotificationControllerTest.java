package com.backendchallenge.messagingservice.infrastructure.controller;

import com.backendchallenge.messagingservice.application.http.dto.OrderReadyRequest;
import com.backendchallenge.messagingservice.application.http.handler.interfaces.INotificationHandler;
import com.backendchallenge.messagingservice.domain.until.ConstJwt;
import com.backendchallenge.messagingservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationControllerTest {

    @Mock
    private INotificationHandler notificationHandler;

    @InjectMocks
    private NotificationController notificationController;
    AutoCloseable closeable;
    @BeforeEach
    void setUp() {
       closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(authorities = ConstJwt.HAS_AUTHORITY_EMPLOYEE)
    void sendNotification_validRequest_returnsOk() {
        OrderReadyRequest request = new OrderReadyRequest();
        ResponseEntity<Void> response = notificationController.sendNotification(request);
        assertEquals(ResponseEntity.ok().build(), response);
        verify(notificationHandler).sendNotification(request);
    }
    @Test
    @WithMockUser(authorities = ConstJwt.HAS_AUTHORITY_EMPLOYEE)
    void existPinByPhoneNumber_validPhone_returnsTrue() {
        String phone = ConstTest.PHONE;
        when(notificationHandler.existPinByPhoneNumber(phone)).thenReturn(true);
        ResponseEntity<Boolean> response = notificationController.existPinByPhoneNumber(phone);
        assertEquals(ResponseEntity.ok(true), response);
    }

    @Test
    @WithMockUser(authorities = ConstJwt.HAS_AUTHORITY_EMPLOYEE)
    void findPinByPhoneNumber_validPhone_returnsPin() {
        String phone = ConstTest.PHONE;
        String pin = ConstTest.PIN_TEST;
        when(notificationHandler.findPinByPhoneNumber(phone)).thenReturn(pin);
        ResponseEntity<String> response = notificationController.findPinByPhoneNumber(phone);
        assertEquals(ResponseEntity.ok(pin), response);
    }

}