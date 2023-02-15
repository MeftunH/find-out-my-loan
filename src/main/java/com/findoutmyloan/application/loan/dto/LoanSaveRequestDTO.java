package com.findoutmyloan.application.loan.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoanSaveRequestDTO implements Serializable {
    @NotNull
    private Long customerId;
    @NotNull
    private PaybackGuaranteeType paybackGuaranteeType;
    @NotNull
    private float amount;
    @NotNull
    private LoanResult result;

    private int creditScore;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}
