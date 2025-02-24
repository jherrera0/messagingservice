package com.backendchallenge.messagingservice.domain.until;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenHolderTest {

    @BeforeEach
    void clearToken() {
        TokenHolder.clear();
    }

    @Test
    void setToken_whenAuthenticationIsNotNull_setsAndReturnsToken() {
        Authentication mockAuthentication = mock(Authentication.class);
        when(mockAuthentication.getCredentials()).thenReturn(ConstTest.CREDENTIALS);

        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);
            String token = TokenHolder.setToken();
            assertNotNull(token);
            assertEquals(ConstJwt.BEARER + ConstJwt.SPLITERSTRING + ConstTest.CREDENTIALS, token);
        }
    }

    @Test
    void getToken_returnsToken() {
        try (MockedStatic<TokenHolder> mockedTokenHolder = mockStatic(TokenHolder.class, Mockito.CALLS_REAL_METHODS)) {
            mockedTokenHolder.when(TokenHolder::setToken).thenReturn(ConstTest.VALID_TOKEN);
            assertEquals(ConstTest.VALID_TOKEN, TokenHolder.getToken());
        }
    }

    @Test
    void getTokenValue_returnsStoredToken() {
        TokenHolder.setToken();
        assertNull(TokenHolder.getTokenValue());

        Authentication mockAuthentication = mock(Authentication.class);
        when(mockAuthentication.getCredentials()).thenReturn(ConstTest.CREDENTIALS);

        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);
            TokenHolder.setToken();
            assertEquals(ConstJwt.BEARER + ConstJwt.SPLITERSTRING + ConstTest.CREDENTIALS, TokenHolder.getTokenValue());
        }
    }

    @Test
    void clear_removesStoredToken() {
        TokenHolder.setToken();
        TokenHolder.clear();
        assertNull(TokenHolder.getTokenValue());
    }
}