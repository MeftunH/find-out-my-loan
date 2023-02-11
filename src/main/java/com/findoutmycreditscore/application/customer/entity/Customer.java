package com.findoutmycreditscore.application.customer.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.customer.enums.PaybackGuaranteeType;
import com.findoutmycreditscore.application.person.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends Person {
    @Column(name = "monthly_income", nullable = false)
    private float monthlyIncome;

    //TODO: Move to credit module
    @Enumerated(EnumType.STRING)
    @Column(name = "payback_guarantee_type", length = 30)
    private PaybackGuaranteeType paybackGuaranteeType;

    @Column(name = "customer_limit")
    private float customerLimit;

    @Column(name = "accountPassword", nullable = false,length=100)
    private String accountPassword;
}
