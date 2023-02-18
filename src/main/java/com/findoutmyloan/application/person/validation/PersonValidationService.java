package com.findoutmyloan.application.person.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.person.entity.Person;

import java.util.Date;

public interface PersonValidationService {
    void validatePhoneNumber(String phoneNumber);
    void validateIsIdentityNoUnique(Person person);
    void validateIsPhoneNoUnique(Person person);
    void validateTurkishIdentityNo(long identityNo);
    void validateBirthDate(Date birthDate);
}
