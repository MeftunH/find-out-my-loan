package com.findoutmyloan.application.customer.dto;

import com.findoutmyloan.application.person.dto.PersonDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * A DTO for the {@link com.findoutmyloan.application.customer.entity.Customer} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerUpdateRequestDTO extends PersonDTO implements Serializable {
    @NotNull
    private final float monthlyIncome;
    @NotNull
    private final float customerLimit;
    private final String password;
}