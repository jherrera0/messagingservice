package com.backendchallenge.messagingservice.domain.usecase;

import com.backendchallenge.messagingservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.messagingservice.domain.spi.IMessagePersistencePort;
import com.backendchallenge.messagingservice.domain.spi.INotificationPersistencePort;
import com.backendchallenge.messagingservice.domain.until.ConstTest;
import com.backendchallenge.messagingservice.domain.until.TokenHolder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;

import java.util.Optional;

import static com.backendchallenge.messagingservice.domain.until.ConstTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationCaseTest {

    @Mock
    private IJwtPersistencePort jwtPersistencePort;

    @Mock
    private INotificationPersistencePort notificationPersistencePort;

    @Mock
    private IMessagePersistencePort messagePersistencePort;

    @InjectMocks
    private NotificationCase notificationCase;


    @Test
    void sendNotification_whenPinExists_updatesPinAndSendsMessage() {
        try (MockedStatic<TokenHolder> mockedTokenHolder = mockStatic(TokenHolder.class)) {
            mockedTokenHolder.when(TokenHolder::getTokenValue).thenReturn(ConstTest.VALID_TOKEN);
            when(notificationPersistencePort.findPinByPhoneNumber(PHONE)).thenReturn(Optional.of(PIN_TEST));
            when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
            notificationCase.sendNotification(ORDER_ID, PHONE);

            verify(notificationPersistencePort).updatePin(eq(PHONE), anyString());
            verify(messagePersistencePort).sendMessage(eq(PHONE), contains(ConstTest.ID_TEST.toString()));
        }
    }

    @Test
    void sendNotification_whenPinDoesNotExist_savesPinAndSendsMessage() {
        try (MockedStatic<TokenHolder> mockedTokenHolder = mockStatic(TokenHolder.class)) {
            mockedTokenHolder.when(TokenHolder::getTokenValue).thenReturn(ConstTest.VALID_TOKEN);
            when(notificationPersistencePort.findPinByPhoneNumber(PHONE)).thenReturn(Optional.empty());
            when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
            notificationCase.sendNotification(ORDER_ID, PHONE);

            verify(notificationPersistencePort).savePin(eq(PHONE), anyString());
            verify(messagePersistencePort).sendMessage(eq(PHONE), contains(ConstTest.ID_TEST.toString()));
        }
    }

    @Test
    void existPinByPhoneNumber_whenPinExists_returnsTrue() {
        when(notificationPersistencePort.findPinByPhoneNumber(PHONE)).thenReturn(Optional.of(PIN_TEST));

        boolean result = notificationCase.existPinByPhoneNumber(PHONE);

        assertTrue(result);
    }

    @Test
    void existPinByPhoneNumber_whenPinDoesNotExist_returnsFalse() {
        when(notificationPersistencePort.findPinByPhoneNumber(PHONE)).thenReturn(Optional.empty());

        boolean result = notificationCase.existPinByPhoneNumber(PHONE);

        assertFalse(result);
    }

    @Test
    void findPinByPhoneNumber_whenPinExists_returnsPin() {
        when(notificationPersistencePort.getPinByPhoneNumber(PHONE)).thenReturn(PIN_TEST);

        String result = notificationCase.findPinByPhoneNumber(PHONE);

        assertEquals(PIN_TEST, result);
    }

    @Test
    void findPinByPhoneNumber_whenPinDoesNotExist_returnsEmptyString() {
        when(notificationPersistencePort.getPinByPhoneNumber(PHONE)).thenReturn(null);

        String result = notificationCase.findPinByPhoneNumber(PHONE);

        assertNull(result);
    }
}