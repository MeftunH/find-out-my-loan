package com.findoutmyloan.application.loan.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerLoanResponseDTO {
    private PaybackGuaranteeType paybackGuaranteeType;
    private float amount;
    private LoanResult result;
    private float customerLimit;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}
