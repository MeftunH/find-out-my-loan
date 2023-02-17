package com.findoutmyloan.application.person.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.person.entity.Person;

import java.security.GeneralSecurityException;

public interface PersonValidationService {
    void validatePhoneNumber(String phoneNumber);
    void validateIsIdentityNoUnique(Person person);
    void validateIsPhoneNoUnique(Person person);
    void validateTurkishIdentityNo(long identityNo);
}
