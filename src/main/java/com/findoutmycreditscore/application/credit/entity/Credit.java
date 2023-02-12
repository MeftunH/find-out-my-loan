package com.findoutmycreditscore.application.credit.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.credit.enums.CreditConclusion;
import com.findoutmycreditscore.application.credit.enums.PaybackGuaranteeType;
import com.findoutmycreditscore.application.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "credit")
public class Credit extends BaseEntity {
    @Id
    @SequenceGenerator(name = "creditSeq", sequenceName = "credit_id_seq")
    @GeneratedValue(generator="creditSeq")
    private Long id;

    @Column(name="customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payback_guarantee_type", length = 30)
    private PaybackGuaranteeType paybackGuaranteeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "conclusion", nullable = false)
    private CreditConclusion conclusion;

}
