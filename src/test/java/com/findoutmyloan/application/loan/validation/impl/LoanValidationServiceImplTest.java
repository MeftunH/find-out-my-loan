package com.findoutmyloan.application.loan.validation.impl;

import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) //allow unnecessary stubs for same time injectMock and spy
class LoanValidationServiceImplTest {

    @Mock
    Loan loan;

    @InjectMocks
    private LoanValidationServiceImpl loanValidationService;

    @BeforeEach
    void setUp() {
        when(loan.getAmount()).thenReturn(1000.0f);
        when(loan.getCustomerId()).thenReturn(1L);
        when(loan.getResult()).thenReturn(LoanResult.APPROVED);
        when(loan.getPaybackGuaranteeType()).thenReturn(PaybackGuaranteeType.SURETY);
    }

    @Test
    void validateLoan() {
        loanValidationService.validateLoan(loan);
        verify(loan, times(2)).getAmount();
        verify(loan, times(1)).getResult();
        verify(loan, times(1)).getPaybackGuaranteeType();
    }

    @Test
    void shouldValidateIsAmountPositiveWithNegativeAmount() {
        // Call the function with a negative amount
        float amount=-100.0f;
        when(loan.getAmount()).thenReturn(amount);

        assertThrows(IllegalFieldException.class, ()->{
            loanValidationService.validateLoan(loan);
        });
    }

    @Test
    void shouldValidateIsAmountPositiveWithNegativeCreditScore() {
        // Call the function with a negative amount
        int creditScore=-100;

        assertThrows(GeneralBusinessException.class, ()->{
            loanValidationService.validateCreditScore(creditScore);
        });
    }

    @Test
    void shouldValidateAreFieldsNotNullFields() {
        // Call the function with a negative amount
        when(loan.getCustomerId()).thenReturn(null);
        when(loan.getResult()).thenReturn(null);
        when(loan.getPaybackGuaranteeType()).thenReturn(null);

        assertThrows(IllegalFieldException.class, ()->{
            loanValidationService.validateLoan(loan);
        });
    }


    @Test
    void testValidateLoanWithPositiveAmount() {

        assertDoesNotThrow(()->loanValidationService.validateLoan(loan));
    }

    @Test
    void testValidateLoanWithNullLoan() {
        assertThrows(NullPointerException.class, ()->loanValidationService.validateLoan(null));
    }

    @Test
    void testValidateCreditScoreWithPositiveCreditScore() {
        // Call the function with a positive credit score
        int creditScore=100;
        assertDoesNotThrow(()->{
            loanValidationService.validateCreditScore(creditScore);
        });
    }

    @Test
    void testValidateCreditScoreWithZeroCreditScore() {
        // Call the function with a zero credit score
        int creditScore=0;
        assertDoesNotThrow(()->{
            loanValidationService.validateCreditScore(creditScore);
        });
    }

    @Test
    void testValidateCustomerProfileWithNonNullCustomerProfile() {
        // Create a sample customer profile

        // Call the service function
        assertDoesNotThrow(()->{
            loanValidationService.validateCustomerProfile(CustomerProfiler.BRONZE);
        });

    }

    @Test
    void testValidateCustomerProfileWithNullCustomerProfile() {
        // Call the service function with null argument
        try {
            loanValidationService.validateCustomerProfile(null);
            fail("Expected exception not thrown");
        } catch (IllegalFieldException e) {
            assertNull(e.getMessage());
        }
    }
}