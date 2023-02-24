package com.findoutmyloan.application.notification.sms.observer;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.entity.Notification;
import com.findoutmyloan.application.notification.observer.NotificationObserver;
import com.findoutmyloan.application.notification.service.impl.NotificationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationObserver implements NotificationObserver {
    private static final Logger logger = LoggerFactory.getLogger(SmsNotificationObserver.class);

    @Override
    public void onNotify(Notification notification) {
        logger.info("SMS message: {} notification is sent to person: {}",notification.getMessage(), notification.getPersonId());
    }
}
