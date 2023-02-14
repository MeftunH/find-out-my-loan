package com.findoutmyloan.application.loan.entity;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.generic.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "loan")
public class Loan extends BaseEntity {
    @Id
    @SequenceGenerator(name = "loanSeq", sequenceName = "loan_id_seq")
    @GeneratedValue(generator="loanSeq")
    private Long id;

    @Column(name="customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payback_guarantee_type", length = 30,nullable=false)
    private PaybackGuaranteeType paybackGuaranteeType;
    @Column(name = "amount", nullable = false)
    private float amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false)
    private LoanResult result;

}
