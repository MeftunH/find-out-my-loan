package com.findoutmyloan.application.collateral.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.CollateralType;
import com.findoutmyloan.application.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "collateral")
public class Collateral extends BaseEntity {
    @Id
    @SequenceGenerator(name = "collateralSeq", sequenceName = "collateral_id_seq")
    @GeneratedValue(generator="collateralSeq")
    private Long id;
    @Column(name="credit_id", nullable = false)
    private long creditId;
    @Enumerated(EnumType.STRING)
    @Column(name = "collateral_type", length = 30,nullable=false)
    private CollateralType collateralType;

    private float worth;
}
