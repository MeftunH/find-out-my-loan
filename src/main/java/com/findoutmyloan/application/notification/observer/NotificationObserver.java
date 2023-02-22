package com.findoutmyloan.application.notification.observer;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.notification.entity.Notification;

public interface NotificationObserver {
    void onNotify(Notification notification);
}
