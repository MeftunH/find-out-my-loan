package com.findoutmyloan.application.loan.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequestFromCustomerDTO {
    @NotNull
    private final Long customerId;
    @NotNull
    private final PaybackGuaranteeType paybackGuaranteeType;
}
