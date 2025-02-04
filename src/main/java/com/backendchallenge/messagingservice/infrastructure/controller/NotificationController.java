package com.backendchallenge.messagingservice.infrastructure.controller;

import com.backendchallenge.messagingservice.application.http.dto.OrderReadyRequest;
import com.backendchallenge.messagingservice.application.http.handler.interfaces.INotificationHandler;
import com.backendchallenge.messagingservice.domain.until.ConstDocumentation;
import com.backendchallenge.messagingservice.domain.until.ConstJwt;
import com.backendchallenge.messagingservice.domain.until.ConstRoute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ConstRoute.NOTIFICATION)
@Tag(name = ConstDocumentation.NOTIFICATION_TAG_NAME, description = ConstDocumentation.NOTIFICATION_TAG_DESCRIPTION)
public class NotificationController {
    private final INotificationHandler notificationHandler;
    @Operation(summary = ConstDocumentation.NOTIFICATION_OPERATION_SUMMARY, description = ConstDocumentation.NOTIFICATION_OPERATION_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstDocumentation.CODE_200, description = ConstDocumentation.CODE_200_DESCRIPTION_NOTIFICATION),
            @ApiResponse(responseCode = ConstDocumentation.CODE_400, description = ConstDocumentation.CODE_400_DESCRIPTION_NOTIFICATION),
            @ApiResponse(responseCode = ConstDocumentation.CODE_403, description = ConstDocumentation.CODE_403_DESCRIPTION_NOTIFICATION),
    })
    @PreAuthorize(ConstJwt.HAS_AUTHORITY_EMPLOYEE)
    @PostMapping(ConstRoute.NOTIFICATION_SEND)
    public ResponseEntity<Void> sendNotification(@RequestBody OrderReadyRequest orderReadyRequest) {
        notificationHandler.sendNotification(orderReadyRequest);
        return ResponseEntity.ok().build();
    }
}
