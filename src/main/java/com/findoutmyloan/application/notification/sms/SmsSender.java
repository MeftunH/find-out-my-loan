package com.findoutmyloan.application.notification.sms;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.sms.request.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
