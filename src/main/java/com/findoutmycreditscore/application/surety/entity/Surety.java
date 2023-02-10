package com.findoutmycreditscore.application.surety.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.person.entity.Person;
import com.findoutmycreditscore.application.surety.enums.SuretyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "surety")
@PrimaryKeyJoinColumn(name = "surety_id")
public class Surety  extends Person {
    @Enumerated(EnumType.STRING)
    @Column(name = "surety_type", length = 30)
    private SuretyType suretyType;

    @Column(name = "to_customer_id", nullable = false)
    private Long toCustomerId;

}
