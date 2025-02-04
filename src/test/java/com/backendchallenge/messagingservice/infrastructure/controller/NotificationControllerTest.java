package com.backendchallenge.messagingservice.infrastructure.controller;

import com.backendchallenge.messagingservice.application.http.dto.OrderReadyRequest;
import com.backendchallenge.messagingservice.application.http.handler.interfaces.INotificationHandler;
import com.backendchallenge.messagingservice.domain.until.ConstJwt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

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
}