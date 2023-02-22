package com.findoutmyloan.application.notification.sms.observer;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.entity.Notification;
import com.findoutmyloan.application.notification.observer.NotificationObserver;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationObserver implements NotificationObserver {
    @Override
    public void onNotify(Notification notification) {
        Logger logger=org.slf4j.LoggerFactory.getLogger(SmsNotificationObserver.class);
        logger.info("SMS notification sent to "+notification.getRecipientName()
                +notification.getRecipientSurname()
                +" with message: "+notification.getMessage());
    }
}
