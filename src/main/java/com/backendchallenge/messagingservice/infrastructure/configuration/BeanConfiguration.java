package com.backendchallenge.messagingservice.infrastructure.configuration;

import com.backendchallenge.messagingservice.application.jpa.adapter.JwtJpaAdapter;
import com.backendchallenge.messagingservice.application.jpa.adapter.MessageJpaAdapter;
import com.backendchallenge.messagingservice.application.jpa.adapter.NotificationPinJpaAdapter;
import com.backendchallenge.messagingservice.application.jpa.repository.INotificationPinRepository;
import com.backendchallenge.messagingservice.application.jwt.JwtService;
import com.backendchallenge.messagingservice.domain.api.INotificationServicePort;
import com.backendchallenge.messagingservice.domain.spi.IJwtPersistencePort;
import com.backendchallenge.messagingservice.domain.spi.IMessagePersistencePort;
import com.backendchallenge.messagingservice.domain.spi.INotificationPersistencePort;
import com.backendchallenge.messagingservice.domain.usecase.NotificationCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final JwtService jwtService;
    private final INotificationPinRepository notificationPinRepository;

    @Bean
    public IJwtPersistencePort jwtPersistencePort(){
        return new JwtJpaAdapter(jwtService);
    }

    @Bean
    public IMessagePersistencePort messagePersistencePort(){
        return new MessageJpaAdapter();
    }

    @Bean
    public INotificationPersistencePort notificationPersistencePort(){
        return new NotificationPinJpaAdapter(notificationPinRepository);
    }

    @Bean
    public INotificationServicePort notificationServicePort(){
        return new NotificationCase(jwtPersistencePort(),notificationPersistencePort(),messagePersistencePort());
    }
}
