package com.findoutmyloan.application.customer.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.validation.CustomerValidationService;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.person.enums.PersonType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Optional;

import static com.findoutmyloan.application.customer.enums.CustomerErrorMessage.*;

@Service
@RequiredArgsConstructor
public class CustomerValidationServiceImpl implements CustomerValidationService {
    private final CustomerRepository customerRepository;

    @Override
    public void validateCustomerByIdentityNoAndBirthDate(Long identityNo, Date birthDate) {
        if (customerRepository.findByIdentityNoAndBirthDate(identityNo, birthDate).equals(Optional.empty()))
            throw new GeneralBusinessException(INFORMATION_MISMATCH);
    }
    @Override
    public void validateCustomerPasswordIsMinimumThreeCharacters(String password) {
        if (password.length()<3)
            throw new GeneralBusinessException(PASSWORD_MUST_BE_AT_LEAST_THREE_CHARACTERS);
    }
    @Override
    public void validateAreFieldsNonNull(Customer customer) {
        boolean hasNullField=customer.getName().isBlank()||customer.getSurname().isBlank()||customer.getBirthDate()==null||customer.getPhoneNumber().isBlank()||String.valueOf(customer.getMonthlyIncome())==null||String.valueOf(customer.getCustomerLimit())==null||String.valueOf(customer.getPersonType())==null||String.valueOf(customer.getIdentityNo())==null||customer.getPassword().isBlank();
        if (hasNullField) {
            throw new IllegalFieldException(FIELD_CANNOT_BE_NULL);
        }
    }

    @Override
    public void validateIsPersonTypeCustomer(Customer customer) {
        if (!customer.getPersonType().equals(PersonType.CUSTOMER)) {
            throw new IllegalFieldException(PERSON_TYPE_MUST_BE_CUSTOMER);
        }
    }

    @Override
    public void validateMonthlyIncome(float monthlyIncome) {
        if (monthlyIncome<0) {
            throw new IllegalFieldException(MONTHLY_INCOME_CANNOT_BE_NEGATIVE);
        }
    }
}