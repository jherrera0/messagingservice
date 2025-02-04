package com.backendchallenge.messagingservice.application.jpa.adapter;

import com.backendchallenge.messagingservice.application.jwt.JwtService;
import com.backendchallenge.messagingservice.domain.spi.IJwtPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtJpaAdapter implements IJwtPersistencePort {
    private final JwtService jwtService;
    @Override
    public Long getUserId(String token) {
        return jwtService.extractId(token);
    }
}
