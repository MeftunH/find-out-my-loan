package com.findoutmycreditscore.application.creditscore.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import jakarta.persistence.*;
import lombok.Data;

@Data
public class CreditScore {
    @Id
    @SequenceGenerator(name = "creditScoreSeq", sequenceName = "credit_score_seq")
    @GeneratedValue(generator="creditScoreSeq", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "creditScore", nullable = false)
    private int creditScore;
}
