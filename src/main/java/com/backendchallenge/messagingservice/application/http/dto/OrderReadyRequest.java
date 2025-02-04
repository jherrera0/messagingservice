package com.backendchallenge.messagingservice.application.http.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReadyRequest {
    @Positive
    @NotNull
    private Long orderId;
    @NotBlank
    private String phone;
}
