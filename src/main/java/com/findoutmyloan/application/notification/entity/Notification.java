package com.findoutmyloan.application.notification.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.entity.BaseEntity;
import com.findoutmyloan.application.notification.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "notification")
@Entity
@Getter
@Setter
public class Notification extends BaseEntity {
    @Id
    @SequenceGenerator(name = "notificationSeq", sequenceName = "notification_id_seq")
    @GeneratedValue(generator = "notificationSeq")
    private Long id;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @Column(name = "person_id", nullable = false)
    private Long personId;
    @Column(name = "message", nullable = false)
    private String message;
}
