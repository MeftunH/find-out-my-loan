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
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
    private  Long id;
    private NotificationType type;
    private Long personId;
    private String message;
}