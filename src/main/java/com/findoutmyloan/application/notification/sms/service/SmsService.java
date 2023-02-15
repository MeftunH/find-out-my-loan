package com.findoutmyloan.application.notification.sms.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.sms.request.SmsRequest;
import com.findoutmyloan.application.notification.sms.sender.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final TwilioSmsSender twilioSmsSender;

    @Autowired
    public SmsService(TwilioSmsSender twilioSmsSender) {
        this.twilioSmsSender=twilioSmsSender;
    }
    public void sendSms(SmsRequest smsRequest) {
        twilioSmsSender.sendSms(smsRequest);
    }
}
