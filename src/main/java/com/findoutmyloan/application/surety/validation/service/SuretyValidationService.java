package com.findoutmyloan.application.surety.validation.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.surety.entity.Surety;

public interface SuretyValidationService {
    void validateIsPersonTypeSurety(Surety surety);
    void validateSuretyType(Surety surety);
    void validateFieldsAreNotNull(Surety surety);
    void validateSurety(Surety surety);
}
