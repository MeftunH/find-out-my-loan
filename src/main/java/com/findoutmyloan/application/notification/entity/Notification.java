package com.findoutmyloan.application.notification.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.entity.BaseEntity;
import com.findoutmyloan.application.notification.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "notification")
@Entity
@Getter
@Setter
public class Notification extends BaseEntity {
    @Id
    @SequenceGenerator(name = "notificationSeq", sequenceName = "notification_id_seq")
    @GeneratedValue(generator="notificationSeq")
    private Long id;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @Column(name = "recipient_name", nullable = false)
   private String recipientName;
   @Column(name = "recipient_surname", nullable = false)
   private String recipientSurname;
   @Column(name = "message", nullable = false)
   private String message;
}
