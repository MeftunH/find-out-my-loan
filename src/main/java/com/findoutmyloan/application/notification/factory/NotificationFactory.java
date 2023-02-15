package com.findoutmyloan.application.notification.factory;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.service.NotificationService;
import com.findoutmyloan.application.notification.service.SmsNotificationService;
import com.findoutmyloan.application.person.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component

public class NotificationFactory {
    private final Map<String, NotificationService> notificationServices;
    private final SmsNotificationService defaultNotificationService;

    public NotificationFactory(Map<String, NotificationService> notificationServices, SmsNotificationService defaultNotificationService) {
        this.notificationServices=notificationServices;
        this.defaultNotificationService=defaultNotificationService;
    }
    public Optional<NotificationService> getNotificationService(Person person) {
        return Optional.ofNullable(notificationServices.get(person));
    }
    public NotificationService getDefaultNotificationService() {
        return defaultNotificationService;
    }
}
