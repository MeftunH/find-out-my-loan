package com.findoutmyloan.application.customer.dto;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.person.dto.PersonDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class CustomerDTO extends PersonDTO implements Serializable {
    @NotNull
    private long id;
    @NotNull
    private float monthlyIncome;
    @NotNull
    private float customerLimit;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}

