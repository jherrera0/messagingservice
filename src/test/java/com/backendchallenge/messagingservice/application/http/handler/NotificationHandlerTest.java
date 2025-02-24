package com.backendchallenge.messagingservice.application.http.handler;

import com.backendchallenge.messagingservice.application.http.dto.OrderReadyRequest;
import com.backendchallenge.messagingservice.domain.api.INotificationServicePort;
import com.backendchallenge.messagingservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void existPinByPhoneNumber_validPhone_returnsTrue() {
        String phone = ConstTest.PHONE;
        when(notificationServicePort.existPinByPhoneNumber(phone)).thenReturn(true);

        boolean result = notificationHandler.existPinByPhoneNumber(phone);

        assertTrue(result);
    }

    @Test
    void findPinByPhoneNumber_validPhone_returnsPin() {
        String phone = ConstTest.PHONE;
        String pin = ConstTest.PIN_TEST;
        when(notificationServicePort.findPinByPhoneNumber(phone)).thenReturn(pin);

        String result = notificationHandler.findPinByPhoneNumber(phone);

        assertEquals(pin, result);

    }
}