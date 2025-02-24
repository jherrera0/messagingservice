package com.backendchallenge.messagingservice.domain.spi;

public interface IJwtPersistencePort {
    Long getUserId(String token);
}
