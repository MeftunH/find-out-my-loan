package com.findoutmyloan.application.notification.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.enums.NotificationType;
import com.findoutmyloan.application.person.entity.Person;

public interface NotificationService {
    void notify(NotificationType type, String message, Person recipient);
}
