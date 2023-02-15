package com.findoutmyloan.application.notification.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.person.entity.Person;

public interface NotificationService {
    void notify(Person person,String message);
}
