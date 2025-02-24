package com.backendchallenge.messagingservice.application.jwt;

import com.backendchallenge.messagingservice.domain.until.ConstTest;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @InjectMocks
    private com.backendchallenge.messagingservice.application.jwt.JwtService jwtService;

    @Mock
    private Claims claims;

    @Value("${app-security-key}")
    private String secretKey = "mysecretkeymysecretkeymysecretkeymysecretkey";

    AutoCloseable closeable;
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void extractUsername_InvalidJwt_ThrowsException() {
        String jwt = ConstTest.INVALID_TOKEN;

        assertThrows(Exception.class, () -> jwtService.extractUsername(jwt));
    }

    @Test
    void extractRole_InvalidJwt_ThrowsException() {
        String jwt = ConstTest.INVALID_TOKEN;

        assertThrows(Exception.class, () -> jwtService.extractRole(jwt));
    }


    @Test
    void extractId_InvalidJwt_ThrowsException() {
        String jwt = ConstTest.INVALID_TOKEN;

        assertThrows(Exception.class, () -> jwtService.extractId(jwt));
    }

}