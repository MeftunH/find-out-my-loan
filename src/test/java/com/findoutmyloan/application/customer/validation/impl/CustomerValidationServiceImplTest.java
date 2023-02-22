package com.findoutmyloan.application.customer.validation.impl;

import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.person.entity.Person;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.person.validation.PersonValidationService;
import com.findoutmyloan.application.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerValidationServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PersonValidationService personValidationService;

    @InjectMocks
    private CustomerValidationServiceImpl customerValidationService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void shouldCustomerByIdentityNoAndBirthDateWhenCustomerIsNotFoundThenThrowException() {
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

    @Test
    void shouldValidateCustomerPasswordIsMinimumThreeCharactersWithInvalidPassword() {
        assertEquals(CustomerErrorMessage.PASSWORD_MUST_BE_AT_LEAST_THREE_CHARACTERS,
                assertThrows(GeneralBusinessException.class, ()->{
                    customerValidationService.validateCustomerPasswordIsMinimumThreeCharacters("ab");
                }).getBaseErrorMessage());
    }
    @Test
    void shouldNotThrowExceptionValidateAreFieldsNonNullWithNonNullFields() {
        // Arrange
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setBirthDate(DateUtil.convertToDate(LocalDate.of(2000, 1, 1)));
        customer.setPhoneNumber("123456789");
        customer.setMonthlyIncome(5000);
        customer.setCustomerLimit(10000);
        customer.setPersonType(PersonType.CUSTOMER);
        customer.setIdentityNo(12345678901L);
        customer.setPassword("password");

        // Act & Assert
        customerValidationService.validateAreFieldsNonNull(customer); // should not throw any exception
    }
    @Test
    void shouldThrowIllegalFieldExceptionValidateAreFieldsNonNullWithNullFields() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setBirthDate(null);

        // Act & Assert
        assertThrows(IllegalFieldException.class, () -> customerValidationService.validateAreFieldsNonNull(customer));
    }
    @Test
    void shouldThrowIllegalFieldExceptionValidateAreFieldsNonNullWithBlankFields() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("");
        customer.setSurname("");
        customer.setBirthDate(DateUtil.convertToDate(LocalDate.of(2000, 1, 1)));
        customer.setPhoneNumber("");
        customer.setMonthlyIncome(5000);
        customer.setCustomerLimit(10000);
        customer.setPersonType(PersonType.CUSTOMER);
        customer.setIdentityNo(12345678901L);
        customer.setPassword("");

        // Act & Assert
        assertThrows(IllegalFieldException.class, () -> customerValidationService.validateAreFieldsNonNull(customer));
    }
    @Test
    void shouldNotThrowExceptionValidateCustomerWithValidCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setBirthDate(DateUtil.convertToDate(LocalDate.of(2000, 1, 1)));
        customer.setPhoneNumber("123456789");
        customer.setPersonType(PersonType.CUSTOMER);
        customer.setIdentityNo(12345678901L);
        customer.setPassword("password");
        // mock the necessary behavior for successful validation
        doNothing().when(personValidationService).validateIsPhoneNoUnique(customer, CustomerErrorMessage.CUSTOMER_PHONE_NUMBER_MUST_BE_UNIQUE);
        doNothing().when(personValidationService).validateIsIdentityNoUnique(customer, CustomerErrorMessage.CUSTOMER_IDENTITY_NO_MUST_BE_UNIQUE);
        // Act & Assert
        assertDoesNotThrow(() -> customerValidationService.validateCustomer(customer));
    }
    @Test
    void shouldThrowExceptionValidateCustomerWithInvalidCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John");
        customer.setSurname("Doe");
        customer.setBirthDate(DateUtil.convertToDate(LocalDate.of(2000, 1, 1)));
        customer.setPhoneNumber("");
        customer.setPersonType(PersonType.CUSTOMER);
        customer.setIdentityNo(0);
        customer.setPassword("password");
        // Act & Assert
        assertThrows(IllegalFieldException.class, () -> customerValidationService.validateCustomer(customer));
    }
    @Test
    void shouldNotThrowExceptionValidateIsPersonTypeCustomerWithValidPersonType() {
        // Arrange
        Customer customer = new Customer();
        customer.setPersonType(PersonType.CUSTOMER);

        // Act & Assert
        customerValidationService.validateIsPersonTypeCustomer(customer); // should not throw any exception
    }

    @Test
    void shouldThrowIllegalFieldExceptionValidateIsPersonTypeCustomerWithInvalidPersonType() {
        // Arrange
        Customer customer = new Customer();
        customer.setPersonType(PersonType.SURETY);

        // Act & Assert
        assertThrows(IllegalFieldException.class, () -> customerValidationService.validateIsPersonTypeCustomer(customer));
    }

    @Test
     void testValidateMonthlyIncome_withPositiveMonthlyIncome_shouldNotThrowException() {
        // Arrange
        float monthlyIncome = 5000;

        // Act & Assert
        assertDoesNotThrow(() -> customerValidationService.validateMonthlyIncome(monthlyIncome));
    }

    @Test
     void testValidateMonthlyIncome_withZeroMonthlyIncome_shouldNotThrowException() {
        // Arrange
        float monthlyIncome = 0;

        // Act & Assert
        assertDoesNotThrow(() -> customerValidationService.validateMonthlyIncome(monthlyIncome));
    }

    @Test
     void testValidateMonthlyIncome_withNegativeMonthlyIncome_shouldThrowIllegalFieldException() {
        // Arrange
        float monthlyIncome = -5000;

        // Act & Assert
        assertThrows(IllegalFieldException.class, () -> customerValidationService.validateMonthlyIncome(monthlyIncome));
    }

}