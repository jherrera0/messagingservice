package com.backendchallenge.messagingservice.application.http.handler;

import com.backendchallenge.messagingservice.application.http.dto.OrderReadyRequest;
import com.backendchallenge.messagingservice.domain.api.INotificationServicePort;
import com.backendchallenge.messagingservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class NotificationHandlerTest {

    @Mock
    private INotificationServicePort notificationServicePort;

    @InjectMocks
    private NotificationHandler notificationHandler;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
       closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendNotification_validRequest_callsServicePort() {
        OrderReadyRequest request = new OrderReadyRequest();
        request.setOrderId(ConstTest.ORDER_ID);
        request.setPhone(ConstTest.PHONE);

        notificationHandler.sendNotification(request);

        verify(notificationServicePort).sendNotification(ConstTest.ORDER_ID, ConstTest.PHONE);
    }

}