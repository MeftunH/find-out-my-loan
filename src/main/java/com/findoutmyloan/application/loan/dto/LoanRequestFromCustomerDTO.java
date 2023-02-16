package com.findoutmyloan.application.loan.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoanRequestFromCustomerDTO implements Serializable {
    @NotNull
    private final PaybackGuaranteeType paybackGuaranteeType;
}
