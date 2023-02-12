package com.findoutmycreditscore.application.credit.dto;

import com.findoutmycreditscore.application.credit.entity.Credit;
import com.findoutmycreditscore.application.credit.enums.CreditConclusion;
import com.findoutmycreditscore.application.credit.enums.PaybackGuaranteeType;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Credit} entity
 */
@Data
public class CreditDto implements Serializable {
    private final Long customerId;
    private final PaybackGuaranteeType paybackGuaranteeType;
    private final CreditConclusion conclusion;
}