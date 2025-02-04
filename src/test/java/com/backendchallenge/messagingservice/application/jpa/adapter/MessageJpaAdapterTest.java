package com.backendchallenge.messagingservice.application.jpa.adapter;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static com.backendchallenge.messagingservice.domain.until.ConstTest.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MessageJpaAdapterTest {


    @InjectMocks
    private MessageJpaAdapter messageJpaAdapter;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendMessage_validPhoneAndMessage_callsTwilioApi() {
        MessageCreator messageCreatorMock = mock(MessageCreator.class);

        Message messageMock = mock(Message.class);

        try (MockedStatic<Message> mockedStatic = Mockito.mockStatic(Message.class)) {
            mockedStatic.when(() -> Message.creator(
                    any(PhoneNumber.class),
                    any(PhoneNumber.class),
                    anyString()
            )).thenReturn(messageCreatorMock);

            when(messageCreatorMock.create()).thenReturn(messageMock);

            messageJpaAdapter.sendMessage(PHONE, MESSAGE_TEST);

            // Verificar que se llamó a create()
            verify(messageCreatorMock).create();
        }
    }
}