package com.findoutmyloan.application.customer.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.person.entity.Person;
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



    @Column(name = "customer_limit")
    private float customerLimit;

    @Column(name = "accountPassword", nullable = false,length=100)
    private String password;
}
