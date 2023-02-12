package com.findoutmycreditscore.application.credit.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.credit.enums.CreditConclusion;
import com.findoutmycreditscore.application.credit.enums.PaybackGuaranteeType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreditSaveRequestDTO implements Serializable {
   @NotNull
    private final Long customerId;

   @NotNull
   private final PaybackGuaranteeType paybackGuaranteeType;

    @NotNull
    private final CreditConclusion conclusion;
}
