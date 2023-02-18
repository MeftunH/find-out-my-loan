package com.findoutmyloan.application.surety.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.enums.SuretyErrorMessage;
import com.findoutmyloan.application.surety.validation.SuretyValidationService;

public class SuretyValidationServiceImpl implements SuretyValidationService {
    @Override
    public void validateIsPersonTypeSurety(Surety surety) {
        if (!surety.getPersonType().equals(PersonType.SURETY))
            throw new IllegalFieldException(SuretyErrorMessage.PERSON_TYPE_MUST_BE_SURETY)
    }

    @Override
    public void validateSuretyType(Surety surety) {

    }

    @Override
    public void validateAreFieldsNull(String suretyType) {

    }
}
