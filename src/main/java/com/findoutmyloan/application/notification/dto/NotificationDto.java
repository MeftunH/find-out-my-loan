package com.findoutmyloan.application.notification.dto;

import com.findoutmyloan.application.notification.entity.Notification;
import com.findoutmyloan.application.notification.enums.NotificationType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Notification} entity
 */
@Data
public class NotificationDto implements Serializable {
    private final Date baseAdditionalFieldsCreatedDate;
    private final Date baseAdditionalFieldsUpdatedDate;
    private final Long id;
    private final NotificationType type;
    private final String recipientName;
    private final String recipientSurname;
    private final String message;
}