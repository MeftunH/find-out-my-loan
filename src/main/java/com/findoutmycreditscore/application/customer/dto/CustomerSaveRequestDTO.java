package com.findoutmycreditscore.application.customer.dto;

import com.findoutmycreditscore.application.customer.entity.Customer;
import com.findoutmycreditscore.application.customer.enums.PaybackGuaranteeType;
import com.findoutmycreditscore.application.person.enums.PersonType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Customer} entity
 */
@Data
public class CustomerSaveRequestDTO implements Serializable {
    private final String name;
    private final String surname;
    private final long identityNo;
    private final Date birthDate;
    private final String phoneNumber;
    private final PersonType personType;
    private final float monthlyIncome;
    private final PaybackGuaranteeType paybackGuaranteeType;
    private final float customerLimit;
}