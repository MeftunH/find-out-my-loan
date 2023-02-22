package com.findoutmyloan.application.notification.sms.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public enum SmsMessageTemplate {
    LOAN_APPLICATION_SUBMITTED("Your loan application has been submitted.")
    ;

    private final String message;

    SmsMessageTemplate(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
