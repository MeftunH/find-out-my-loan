package com.findoutmyloan.application.person.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;
import com.findoutmyloan.application.person.entity.Person;

import java.util.Date;

public interface PersonValidationService {
    void validatePhoneNumber(String phoneNumber, BaseErrorMessage baseErrorMessage);
    void validateIsIdentityNoUnique(Person person, BaseErrorMessage baseErrorMessage);
    void validateIsPhoneNoUnique(Person person, BaseErrorMessage baseErrorMessage);
    void validateTurkishIdentityNo(long identityNo, BaseErrorMessage baseErrorMessage);
    void validateBirthDate(Date birthDate, BaseErrorMessage baseErrorMessage);
}
