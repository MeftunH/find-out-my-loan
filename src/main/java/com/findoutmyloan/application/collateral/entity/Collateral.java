package com.findoutmyloan.application.collateral.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "collateral")
public class Collateral extends BaseEntity {
    @Id
    @SequenceGenerator(name = "collateralSeq", sequenceName = "collateral_id_seq")
    @GeneratedValue(generator="collateralSeq")
    private Long id;
    @Column(name="customer_id", nullable = false)
    private long customerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "collateral_type", length = 30,nullable=false)
    private CollateralType collateralType;

    private float worth;
}
