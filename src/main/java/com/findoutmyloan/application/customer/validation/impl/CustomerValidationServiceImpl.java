package com.findoutmyloan.application.customer.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.validation.CustomerValidationService;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.log.SingletonLogger;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.person.validation.PersonValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.Optional;

import static com.findoutmyloan.application.customer.enums.CustomerErrorMessage.*;

@Service
@RequiredArgsConstructor
@ControllerAdvice
public class CustomerValidationServiceImpl implements CustomerValidationService {
    private final SingletonLogger logger=SingletonLogger.getInstance();
    private final CustomerRepository customerRepository;
    private final PersonValidationService personValidationService;

    @Override
    public void validateCustomerByIdentityNoAndBirthDate(Long identityNo, Date birthDate) {
        if (customerRepository.findByIdentityNoAndBirthDate(identityNo, birthDate).equals(Optional.empty())) {
            logger.warn("Customer with identityNo "+identityNo+" and birthDate "+birthDate+"+ does not exist");
            throw new IllegalFieldException(INFORMATION_MISMATCH);
        }
    }

    @Override
    public void validateCustomerPasswordIsMinimumThreeCharacters(String password) {
        if (password.length()<3)
            throw new IllegalFieldException(PASSWORD_MUST_BE_AT_LEAST_THREE_CHARACTERS);
    }

    @Override
    public void validateAreFieldsNonNull(Customer customer) {
        boolean hasNullField=customer.getName().isBlank()||customer.getSurname().isBlank()||customer.getBirthDate()==null||customer.getPhoneNumber().isBlank()||String.valueOf(customer.getMonthlyIncome())==null||String.valueOf(customer.getCustomerLimit())==null||String.valueOf(customer.getPersonType())==null||String.valueOf(customer.getIdentityNo())==null||customer.getPassword().isBlank();
        if (hasNullField) {
            logger.warn("Customer "+customer+" fields are null");
            throw new IllegalFieldException(FIELD_CANNOT_BE_NULL);
        }
    }

    @Override
    public void validateCustomer(Customer customer) {
        validateAreFieldsNonNull(customer);
        validateIsPersonTypeCustomer(customer);
        validateMonthlyIncome(customer.getMonthlyIncome());
        validateCustomerPasswordIsMinimumThreeCharacters(customer.getPassword());
        personValidationService.validateTurkishIdentityNo(customer.getIdentityNo(), CUSTOMER_IDENTITY_NO_INVALID);
        personValidationService.validateIsIdentityNoUnique(customer, CUSTOMER_IDENTITY_NO_MUST_BE_UNIQUE);
        personValidationService.validatePhoneNumber(customer.getPhoneNumber(), CUSTOMER_PHONE_NUMBER_INVALID);
        personValidationService.validateIsPhoneNoUnique(customer, CUSTOMER_PHONE_NUMBER_MUST_BE_UNIQUE);
        personValidationService.validateBirthDate(customer.getBirthDate(), CUSTOMER_BIRTH_DATE_INVALID);
    }

    @Override
    public void validateIsPersonTypeCustomer(Customer customer) {
        if (!customer.getPersonType().equals(PersonType.CUSTOMER)) {
            logger.warn("Person type "+customer.getPersonType()+" is not customer");
            throw new IllegalFieldException(PERSON_TYPE_MUST_BE_CUSTOMER);
        }
    }

    @Override
    public void validateMonthlyIncome(float monthlyIncome) {
        if (monthlyIncome<0) {
            logger.warn("Monthly income "+monthlyIncome+" is negative");
            throw new IllegalFieldException(MONTHLY_INCOME_CANNOT_BE_NEGATIVE);
        }
    }
}