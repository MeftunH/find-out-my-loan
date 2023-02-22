package com.findoutmyloan.application.notification.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum NotificationErrorMessage implements BaseErrorMessage {
  NOTIFICATION_TYPE_NOT_SUPPORTED("Notification type is not supported", "Notification type is not supported")
    ;
    private final String message;
    private final String detailMessage;

    NotificationErrorMessage(String message, String detailMessage) {
        this.message=message;
        this.detailMessage=detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }
}
