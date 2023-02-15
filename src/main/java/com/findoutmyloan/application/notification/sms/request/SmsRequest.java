package com.findoutmyloan.application.notification.sms.request;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SmsRequest {
    private final String phoneNumber;
    private final String message;

    public SmsRequest(String phoneNumber, String message) {
        this.phoneNumber=phoneNumber;
        this.message=message;
    }
}
