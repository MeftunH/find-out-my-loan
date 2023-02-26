package com.findoutmyloan.application.notification.sms.observer;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.log.SingletonLogger;
import com.findoutmyloan.application.notification.entity.Notification;
import com.findoutmyloan.application.notification.observer.NotificationObserver;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationObserver implements NotificationObserver {
    private final SingletonLogger logger=SingletonLogger.getInstance();

    @Override
    public void onNotify(Notification notification) {
        logger.info("SMS notification message: "+notification.getMessage()+"is sent to person:"+notification.getPersonId());
    }
}
