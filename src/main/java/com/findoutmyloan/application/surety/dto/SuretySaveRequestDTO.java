package com.findoutmyloan.application.surety.dto;

import com.findoutmyloan.application.person.dto.PersonDTO;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.enums.SuretyType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * A DTO for the {@link Surety} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SuretySaveRequestDTO extends PersonDTO implements Serializable {
    private final SuretyType suretyType;
    private final Long toCustomerId;
}