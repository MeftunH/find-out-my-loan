package com.findoutmyloan.application.notification.sms.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.entity.BaseEntity;
import com.findoutmyloan.application.notification.entity.Notification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "sms")
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "notification_id")
public class Sms extends Notification {
    private String phoneNumber;
}
