package com.findoutmyloan.application.customer.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerActivityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "customer_activity")
@Getter
@Setter
@Entity
public class CustomerActivity {
    @GeneratedValue(generator="customerActivitySeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customerActivitySeq", sequenceName = "customer_activity_id_seq")
    @Id
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_activity_type", nullable = false,length=30)
    private CustomerActivityType customerActivityType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "process_date", nullable = false)
    private Date processDate;

    @Column(name = "current_balance", nullable = false)
    private float currentBalance;
}
