package com.findoutmycreditscore.application.customer.dto;

import com.findoutmycreditscore.application.customer.entity.Customer;
import com.findoutmycreditscore.application.customer.enums.PaybackGuaranteeType;
import com.findoutmycreditscore.application.person.dto.PersonDTO;
import com.findoutmycreditscore.application.person.enums.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Customer} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CustomerDTO extends PersonDTO implements Serializable {
    private final float monthlyIncome;
    private final PaybackGuaranteeType paybackGuaranteeType;
    private final float customerLimit;
}