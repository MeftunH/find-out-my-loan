package com.findoutmyloan.application.loan.dto;

import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Loan} entity
 */
@Data
public class LoanDTO implements Serializable {
    private final Long customerId;
    private final PaybackGuaranteeType paybackGuaranteeType;
    private float amount;
    private final LoanResult result;
}