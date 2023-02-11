package com.findoutmycreditscore.application.creditscore.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "credit_score")
public class CreditScore extends BaseEntity {
    @Id
    @SequenceGenerator(name = "creditScoreSeq", sequenceName = "credit_score_id_seq")
    @GeneratedValue(generator="creditScoreSeq", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "creditScore", nullable = false)
    private int creditScore;

    @Column(name = "customer_identity_number", nullable = false,length=11)
    private long customerIdentityNumber;
}
