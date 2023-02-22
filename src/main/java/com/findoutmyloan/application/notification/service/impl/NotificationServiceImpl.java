package com.findoutmyloan.application.notification.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.notification.entity.Notification;
import com.findoutmyloan.application.notification.enums.NotificationErrorMessage;
import com.findoutmyloan.application.notification.enums.NotificationType;
import com.findoutmyloan.application.notification.factory.NotificationFactory;
import com.findoutmyloan.application.notification.observer.NotificationObserver;
import com.findoutmyloan.application.notification.service.NotificationService;
import com.findoutmyloan.application.notification.sms.factory.SmsNotificationFactory;
import com.findoutmyloan.application.person.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final List<NotificationObserver> observers;
    private final NotificationFactory smsNotificationFactory;

    @Override
    public void notify(NotificationType type, String message, Person recipient) {
        Notification notification;
        if (type == NotificationType.SMS) {
            notification = smsNotificationFactory.createNotification(type, message,recipient);
        } else {
            throw new GeneralBusinessException(NotificationErrorMessage.NOTIFICATION_TYPE_NOT_SUPPORTED);
        }

        for (NotificationObserver observer : observers) {
            observer.onNotify(notification);
        }
    }
}
