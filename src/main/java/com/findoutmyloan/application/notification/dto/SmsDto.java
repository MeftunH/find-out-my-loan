package com.findoutmyloan.application.notification.dto;

import com.findoutmyloan.application.notification.entity.Sms;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Sms} entity
 */
@Data
@Builder
public class SmsDto implements Serializable {
    private String message;
    private String phoneNumber;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;

}