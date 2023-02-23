package com.findoutmyloan.application.surety.validation.service.impl;

import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.person.validation.PersonValidationService;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.enums.SuretyType;
import com.findoutmyloan.application.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuretyValidationServiceImplTest {
    @Mock
    private PersonValidationService personValidationService;
    @Mock
    private Surety surety;
    @InjectMocks
    @Spy
    private SuretyValidationServiceImpl suretyValidationService;
    @Test
     void testValidateIsPersonTypeSuretyWithSurety() {
        when(surety.getPersonType()).thenReturn(PersonType.SURETY);
        suretyValidationService.validateIsPersonTypeSurety(surety);
        verify(surety, times(1)).getPersonType();
    }

    @Test
    void testValidateIsPersonTypeSuretyWithOtherPersonType() {
        when(surety.getPersonType()).thenReturn(PersonType.CUSTOMER);
        assertThrows(IllegalFieldException.class, () -> {
            suretyValidationService.validateIsPersonTypeSurety(surety);
        });
    }

    @Test
     void testValidateSuretyType_HappyPath() {
        Surety surety = mock(Surety.class);
        when(surety.getSuretyType()).thenReturn(SuretyType.ORDINARY);

        suretyValidationService.validateSuretyType(surety);
        verify(surety, times(1)).getSuretyType();


        // verify that the method did not throw any exceptions
    }

    @Test
     void testValidateSuretyType_UnhappyPath() {
        Surety surety = mock(Surety.class);
        when(surety.getSuretyType()).thenReturn(null);
        assertThrows(IllegalFieldException.class, () -> {
            suretyValidationService.validateSuretyType(surety);
        });
    }

    @Test
    public void testValidateFieldsAreNotNull_AllFieldsNotNull() {
        // Arrange
        Surety surety = new Surety();
        surety.setName("John");
        surety.setSurname("Doe");
        surety.setBirthDate(DateUtil.convertToDate(LocalDate.of(2000, 1, 1)));
        surety.setPhoneNumber("5357711845");
        surety.setPersonType(PersonType.SURETY);
        surety.setIdentityNo(45586784456L);

        // Act
        suretyValidationService.validateFieldsAreNotNull(surety);

        // Assert
        // If no exception was thrown, the test passes
       assertDoesNotThrow(() -> {
           suretyValidationService.validateFieldsAreNotNull(surety);
       });
    }

    @Test
    public void testValidateFieldsAreNotNull_AllFieldsNull() {
        // Arrange
        Surety surety = new Surety();
        surety.setName("");
        surety.setSurname("");
        surety.setBirthDate(null);
        surety.setPhoneNumber("5357711845");
        surety.setPersonType(null);
        surety.setIdentityNo(0L);

        assertThrows(IllegalFieldException.class,() -> {
            suretyValidationService.validateFieldsAreNotNull(surety);
        });
    }


    @Test
    void validateSurety() {
        Surety surety = new Surety();
        surety.setName("John");
        surety.setSurname("Doe");
        surety.setBirthDate(DateUtil.convertToDate(LocalDate.of(2000, 1, 1)));
        surety.setPhoneNumber("5357711845");
        surety.setPersonType(PersonType.SURETY);
        surety.setIdentityNo(45586784456L);
        surety.setSuretyType(SuretyType.CO_GUARANTEES);
        assertDoesNotThrow(() -> {
            suretyValidationService.validateSurety(surety);
        });
    }
}