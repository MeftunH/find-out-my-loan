package com.findoutmycreditscore.application.customer.dto;

import com.findoutmycreditscore.application.customer.entity.Customer;
import com.findoutmycreditscore.application.credit.enums.PaybackGuaranteeType;
import com.findoutmycreditscore.application.person.dto.PersonDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * A DTO for the {@link Customer} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerSaveRequestDTO extends PersonDTO implements Serializable {
    private final float monthlyIncome;

    private final float customerLimit;
    private final String accountPassword;
}