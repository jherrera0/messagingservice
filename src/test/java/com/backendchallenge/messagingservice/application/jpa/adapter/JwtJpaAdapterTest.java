package com.backendchallenge.messagingservice.application.jpa.adapter;

import com.backendchallenge.messagingservice.application.jwt.JwtService;
import com.backendchallenge.messagingservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtJpaAdapterTest {

    private JwtService jwtService;
    private JwtJpaAdapter jwtJpaAdapter;

    @BeforeEach
    void setUp() {
        jwtService = mock(JwtService.class);
        jwtJpaAdapter = new JwtJpaAdapter(jwtService);
    }

    @Test
    void getUserId_shouldReturnUserIdWhenTokenIsValid() {
        String token = ConstTest.VALID_TOKEN;
        Long expectedUserId = ConstTest.ID_TEST;
        when(jwtService.extractId(token)).thenReturn(expectedUserId);

        Long actualUserId = jwtJpaAdapter.getUserId(token);

        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    void getUserId_shouldReturnNullWhenTokenIsInvalid() {
        String token = ConstTest.VALID_TOKEN;
        when(jwtService.extractId(token)).thenReturn(null);

        Long actualUserId = jwtJpaAdapter.getUserId(token);

        assertNull(actualUserId);
    }

}