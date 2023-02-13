package com.findoutmyloan.application.surety.dto;

import com.findoutmyloan.application.person.dto.PersonDTO;
import com.findoutmyloan.application.surety.enums.SuretyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * A DTO for the {@link com.findoutmyloan.application.surety.entity.Surety} entity
 */

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class SuretyDTO extends PersonDTO implements Serializable {
    private final Long id;
    private final SuretyType suretyType;
    private final Long toCustomerId;
}