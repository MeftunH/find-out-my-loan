package com.findoutmyloan.application.person.validation.impl;

import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.person.entity.Person;
import com.findoutmyloan.application.person.entity.PersonRepository;
import com.findoutmyloan.application.person.enums.PersonErrorMessage;
import com.findoutmyloan.application.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonValidationServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonValidationServiceImpl personService;

    @Test
    void shouldValidatePhoneNumberDoesNotThrowException() {
        // Arrange
        String phoneNumber="5855555556";
        assertDoesNotThrow(()->{
            personService.validatePhoneNumber(phoneNumber, CustomerErrorMessage.CUSTOMER_PHONE_NUMBER_INVALID);
        });
    }

    @Test
    void shouldValidatePhoneNumberDoesThrowCustomerPhoneNumberInvalidException() {
        // Arrange
        String phoneNumber="123";
        BaseErrorMessage baseErrorMessage=CustomerErrorMessage.CUSTOMER_PHONE_NUMBER_INVALID;

        // Act and Assert
        assertThrows(IllegalFieldException.class, ()->{
            personService.validatePhoneNumber(phoneNumber, baseErrorMessage);
        });
    }

    @Test
    void shouldValidateIsIdentityNoUniqueDoesThrowException() {
        // Arrange
        Person person=mock(Person.class);
        person.setIdentityNo(12345678901L);
        BaseErrorMessage baseErrorMessage=CustomerErrorMessage.CUSTOMER_IDENTITY_NO_MUST_BE_UNIQUE;
        // Mock
        when(personRepository.findByIdentityNo(person.getIdentityNo()))
                .thenReturn(null);

        // Act and Assert
        personService.validateIsIdentityNoUnique(person, baseErrorMessage);
    }

    @Test
    void shouldValidateIsIdentityNoUniqueDoesNotThrowException() {
        // Arrange
        Person person=mock(Person.class);
        person.setIdentityNo(12345678901L);
        BaseErrorMessage baseErrorMessage=CustomerErrorMessage.CUSTOMER_IDENTITY_NO_MUST_BE_UNIQUE;

        // Mock
        when(personRepository.findByIdentityNo(person.getIdentityNo()))
                .thenReturn(person);
        personService.validateIsIdentityNoUnique(person, baseErrorMessage);
        // Act and Assert
        verify(personRepository, times(1)).findByIdentityNo(person.getIdentityNo());
    }
    @Test
    void shouldValidateIsPhoneNumberUniqueDoesThrowException() {
        // Arrange
        Person person=mock(Person.class);
        person.setPhoneNumber("5357711845");
        BaseErrorMessage baseErrorMessage=CustomerErrorMessage.CUSTOMER_PHONE_NUMBER_MUST_BE_UNIQUE;
        // Mock
        when(personRepository.findByPhoneNumber(person.getPhoneNumber()))
                .thenReturn(null);

        // Act and Assert
        personService.validateIsPhoneNoUnique(person, baseErrorMessage);
    }

    @Test
    void shouldValidateIsPhoneNumberUniqueDoesNotThrowException() {
        // Arrange
        Person person=mock(Person.class);
        person.setPhoneNumber("5357711845");
        BaseErrorMessage baseErrorMessage=CustomerErrorMessage.CUSTOMER_IDENTITY_NO_MUST_BE_UNIQUE;

        // Mock
        when(personRepository.findByPhoneNumber(person.getPhoneNumber()))
                .thenReturn(person);
        personService.validateIsPhoneNoUnique(person, baseErrorMessage);
        // Act and Assert
        verify(personRepository, times(1)).findByPhoneNumber(person.getPhoneNumber());
    }

    @Test
    void shouldValidateTurkishIdentityNo() {
        // Arrange
        long identityNo = 76492030832L;
        BaseErrorMessage baseErrorMessage = CustomerErrorMessage.CUSTOMER_IDENTITY_NO_INVALID;

        // Act and Assert
        personService.validateTurkishIdentityNo(identityNo, baseErrorMessage);
    }

    @Test
    void shouldValidateTurkishIdentityNoThrowException() {
        // Arrange
        long identityNo = 123L;
        BaseErrorMessage baseErrorMessage = CustomerErrorMessage.CUSTOMER_IDENTITY_NO_INVALID;

        // Act and Assert
        assertThrows(IllegalFieldException.class, () -> {
            personService.validateTurkishIdentityNo(identityNo, baseErrorMessage);
        });
    }

    @Test
    void shouldValidateBirthDate() {
        // Arrange
        Date birthDate = DateUtil.convertToDate(LocalDate.now().minusYears(10));

        BaseErrorMessage baseErrorMessage = CustomerErrorMessage.CUSTOMER_BIRTH_DATE_INVALID;

        // Act and Assert
        personService.validateBirthDate(birthDate, baseErrorMessage);
    }

    @Test
    void shouldValidateBirthDateThrowException() {
        // Arrange
        Date birthDate = new Date(Long.MAX_VALUE);
        BaseErrorMessage baseErrorMessage = CustomerErrorMessage.CUSTOMER_BIRTH_DATE_INVALID;

        // Act and Assert
        assertThrows(IllegalFieldException.class, () -> {
            personService.validateBirthDate(birthDate, baseErrorMessage);
        });
    }
}