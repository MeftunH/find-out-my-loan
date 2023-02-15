package com.findoutmyloan.application.notification.sms.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.errorMessage.BaseErrorMessage;

public enum SmsErrorMessage implements BaseErrorMessage {
    PHONE_NUMBER_IS_NOT_VALID("Phone number is not a valid number","Please check the phone number")
    ;
    private  final String message;
    private  final String detailMessage;

    SmsErrorMessage(String message, String detailMessage) {
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
