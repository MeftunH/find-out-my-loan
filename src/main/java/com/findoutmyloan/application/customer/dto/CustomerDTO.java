package com.findoutmyloan.application.customer.dto;

import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.person.dto.PersonDTO;
import jakarta.validation.constraints.NotNull;
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
   private long id;
   @NotNull
    private float monthlyIncome;
    @NotNull
    private final float customerLimit;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}