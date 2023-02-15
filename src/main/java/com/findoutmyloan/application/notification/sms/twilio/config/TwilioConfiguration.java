package com.findoutmyloan.application.notification.sms.twilio.config;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfiguration {
    private String accountSid;
    private String authToken;
    private String trialNumber;
}
