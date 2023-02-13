package com.findoutmyloan.application.loan.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.enums.CreditConclusion;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoanSaveRequestDTO implements Serializable {
   @NotNull
    private final Long customerId;
   @NotNull
   private final PaybackGuaranteeType paybackGuaranteeType;
   @NotNull
    private float amount;
    @NotNull
    private final CreditConclusion conclusion;
}
