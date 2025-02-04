package com.backendchallenge.messagingservice.domain.usecase;

import com.backendchallenge.messagingservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.messagingservice.domain.spi.IMessagePersistencePort;
import com.backendchallenge.messagingservice.domain.spi.INotificationPersistencePort;
import com.backendchallenge.messagingservice.domain.until.ConstTest;
import com.backendchallenge.messagingservice.domain.until.TokenHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockedStatic;

import java.util.Optional;

import static com.backendchallenge.messagingservice.domain.until.ConstTest.*;
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

    @BeforeEach
    void setUp() {
        when(jwtPersistencePort.getUserId(anyString())).thenReturn(ConstTest.ID_TEST);
    }

    @Test
    void sendNotification_whenPinExists_updatesPinAndSendsMessage() {
        try (MockedStatic<TokenHolder> mockedTokenHolder = mockStatic(TokenHolder.class)) {
            mockedTokenHolder.when(TokenHolder::getTokenValue).thenReturn(ConstTest.VALID_TOKEN);
            when(notificationPersistencePort.findPinByPhoneNumber(PHONE)).thenReturn(Optional.of(PIN_TEST));

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

            notificationCase.sendNotification(ORDER_ID, PHONE);

            verify(notificationPersistencePort).savePin(eq(PHONE), anyString());
            verify(messagePersistencePort).sendMessage(eq(PHONE), contains(ConstTest.ID_TEST.toString()));
        }
    }
}