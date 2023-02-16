package com.findoutmyloan.application.surety.dto;

import com.findoutmyloan.application.person.dto.PersonDTO;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.enums.SuretyType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Surety} entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class SuretySaveRequestDTO extends PersonDTO implements Serializable {
    private SuretyType suretyType;
    private Date baseAdditionalFieldsCreatedDate;
    private Date baseAdditionalFieldsUpdatedDate;
}