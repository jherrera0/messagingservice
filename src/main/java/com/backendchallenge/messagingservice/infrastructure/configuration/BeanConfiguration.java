package com.backendchallenge.messagingservice.infrastructure.configuration;

import com.backendchallenge.messagingservice.application.jpa.adapter.JwtJpaAdapter;
import com.backendchallenge.messagingservice.application.jwt.JwtService;
import com.backendchallenge.messagingservice.domain.spi.IJwtPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final JwtService jwtService;

    @Bean
    public IJwtPersistencePort jwtPersistencePort(){
        return new JwtJpaAdapter(jwtService);    }
}
