package com.findoutmyloan.application.surety.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.entity.Surety;

public interface SuretyValidationService {
    void validateIsPersonTypeSurety(Surety surety);
    void validateSuretyType(Surety surety);
    void validateFieldsAreNotNull(Surety surety);
    void validateSurety(Surety surety);
}
