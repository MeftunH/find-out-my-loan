package com.findoutmyloan.application.customer.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.entity.Customer;

import java.security.GeneralSecurityException;
import java.util.Date;

public interface CustomerValidationService{
    void validateCustomerByIdentityNoAndBirthDate(Long identityNo, Date birthDate);
    void validateAreFieldsNonNull(Customer customer);
    void validateMonthlyIncome(float monthlyIncome);
    void validateCustomerPasswordIsMinimumThreeCharacters(String password);
}
