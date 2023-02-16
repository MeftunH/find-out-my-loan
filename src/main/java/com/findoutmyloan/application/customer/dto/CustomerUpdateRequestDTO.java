package com.findoutmyloan.application.customer.dto;

import com.findoutmyloan.application.person.dto.PersonDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.findoutmyloan.application.customer.entity.Customer} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerUpdateRequestDTO extends PersonDTO implements Serializable {
    @NotNull
    private float monthlyIncome;
    private String password;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}