package com.backendchallenge.messagingservice.domain.until;

public class ConstDocumentation {

    public static final String NOTIFICATION_TAG_NAME = "Notification Api";
    public static final String NOTIFICATION_TAG_DESCRIPTION = "Service to send notifications to users";
    public static final String NOTIFICATION_OPERATION_SUMMARY = "Send a notification";
    public static final String NOTIFICATION_OPERATION_DESCRIPTION = "Send a notification to a user";

    public static final String CODE_200 = "200";
    public static final String CODE_400 = "400";
    public static final String CODE_403 = "403";
    public static final String CODE_200_DESCRIPTION_NOTIFICATION = "Notification sent successfully";
    public static final String CODE_400_DESCRIPTION_NOTIFICATION = "Invalid data to send notification";
    public static final String CODE_403_DESCRIPTION_NOTIFICATION = "User not authorized to send notifications";

    private ConstDocumentation() {
    }
}
