package com.findoutmyloan.application.notification.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.log.SingletonLogger;
import com.findoutmyloan.application.notification.entity.Notification;
import com.findoutmyloan.application.notification.enums.NotificationErrorMessage;
import com.findoutmyloan.application.notification.enums.NotificationType;
import com.findoutmyloan.application.notification.factory.NotificationFactory;
import com.findoutmyloan.application.notification.observer.NotificationObserver;
import com.findoutmyloan.application.notification.service.NotificationService;
import com.findoutmyloan.application.person.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService {
    private final List<NotificationObserver> observers;
    private final NotificationFactory smsNotificationFactory;
    private final SingletonLogger logger=SingletonLogger.getInstance();

    private static Notification getNotificationForSave(NotificationType type, String message, Person recipient) {
        Notification notificationForSave=new Notification();
        notificationForSave.setPersonId(recipient.getId());
        notificationForSave.setType(type);
        notificationForSave.setMessage(message);
        return notificationForSave;
    }

    @Override
    public void notify(NotificationType type, String message, Person recipient) {
        Notification notification=getNotificationForSave(type, message, recipient);

        if (type==NotificationType.SMS) {
            notification=smsNotificationFactory.createNotification(type, message, recipient);
            logger.info("SMS notification is created");
        } else {
            logger.warn("Notification type: "+type+"is not supported");
            throw new GeneralBusinessException(NotificationErrorMessage.NOTIFICATION_TYPE_NOT_SUPPORTED);
        }

        for (NotificationObserver observer : observers) {
            observer.onNotify(notification);
        }
    }
}
