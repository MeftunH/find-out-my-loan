package com.findoutmyloan.application.notification.factory;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.entity.Notification;
import com.findoutmyloan.application.notification.enums.NotificationType;
import com.findoutmyloan.application.person.entity.Person;

public interface NotificationFactory {
    Notification createNotification(NotificationType notificationType, String message,
                                    Person recipient);
}
