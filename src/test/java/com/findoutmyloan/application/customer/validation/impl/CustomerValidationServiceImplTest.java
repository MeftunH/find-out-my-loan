package com.findoutmyloan.application.customer.validation.impl;

import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.person.enums.PersonErrorMessage;
import com.findoutmyloan.application.person.validation.PersonValidationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerValidationServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PersonValidationService personValidationService;

    @InjectMocks
    private CustomerValidationServiceImpl customerValidationService;

    @Test
    @DisplayName("Should throw an exception when the customer is not found")
    void validateCustomerByIdentityNoAndBirthDateWhenCustomerIsNotFoundThenThrowException() {
        Long identityNo=12345678910L;
        Date birthDate=new Date();
        when(customerRepository.findByIdentityNoAndBirthDate(identityNo, birthDate))
                .thenReturn(Optional.empty());
        GeneralBusinessException exception=
                assertThrows(
                        GeneralBusinessException.class,
                        ()->
                                customerValidationService.validateCustomerByIdentityNoAndBirthDate(
                                        identityNo, birthDate));
        assertEquals(CustomerErrorMessage.INFORMATION_MISMATCH, exception.getBaseErrorMessage());
    }
}