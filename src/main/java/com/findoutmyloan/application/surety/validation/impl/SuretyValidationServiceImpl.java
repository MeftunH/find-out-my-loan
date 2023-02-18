package com.findoutmyloan.application.surety.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.person.validation.PersonValidationService;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.enums.SuretyErrorMessage;
import com.findoutmyloan.application.surety.validation.SuretyValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SuretyValidationServiceImpl implements SuretyValidationService {
    private final PersonValidationService personValidationService;
    @Override
    public void validateIsPersonTypeSurety(Surety surety) {
        if (!surety.getPersonType().equals(PersonType.SURETY))
            throw new IllegalFieldException(SuretyErrorMessage.PERSON_TYPE_MUST_BE_SURETY);
    }
    @Override
    public void validateSuretyType(Surety surety) {
        if (surety.getSuretyType() == null)
            throw new IllegalFieldException(SuretyErrorMessage.SURETY_TYPE_MUST_NOT_BE_NULL);
    }

    @Override
    public void validateSurety(Surety surety) {
        personValidationService.validateIsPhoneNoUnique(surety, SuretyErrorMessage.SURETY_PHONE_NUMBER_MUST_BE_UNIQUE);
        personValidationService.validateBirthDate(surety.getBirthDate(), SuretyErrorMessage.SURETY_BIRTH_DATE_INVALID);
        personValidationService.validateTurkishIdentityNo(surety.getIdentityNo(), SuretyErrorMessage.SURETY_IDENTITY_NO_INVALID);
        personValidationService.validateIsIdentityNoUnique(surety, SuretyErrorMessage.SURETY_IDENTITY_NO_MUST_BE_UNIQUE);
        validateIsPersonTypeSurety(surety);
        validateSuretyType(surety);
    }

}
