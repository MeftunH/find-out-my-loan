package com.findoutmyloan.application.notification.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "sms")
@Getter
@Setter
@Entity
public class Sms extends BaseEntity {
    @Id
    @SequenceGenerator(name = "smsSeq", sequenceName = "sms_id_seq")
    @GeneratedValue(generator="smsSeq")
    private Long id;
    @Column(name = "message", nullable = false)
    private String message;
    private String phoneNumber;
}
