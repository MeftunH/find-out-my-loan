package com.findoutmycreditscore.application.surety.dto;

import com.findoutmycreditscore.application.person.dto.PersonDTO;
import com.findoutmycreditscore.application.person.enums.PersonType;
import com.findoutmycreditscore.application.surety.enums.SuretyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.findoutmycreditscore.application.surety.entity.Surety} entity
 */

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class SuretyDTO extends PersonDTO implements Serializable {
    private final Long id;
    private final SuretyType suretyType;
    private final Long toCustomerId;
}