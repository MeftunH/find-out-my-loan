package com.findoutmyloan.application.surety.validation.command;

import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.validation.service.SuretyValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuretyValidationCommandTest {
    @Mock
    private SuretyValidationService suretyValidationService;
    @InjectMocks
    private SuretyValidationCommand suretyValidationCommand;

    private LoanApplicationRequestDTO loanApplicationRequestDTO;
    @BeforeEach
    void setUp() {
        loanApplicationRequestDTO =mock(LoanApplicationRequestDTO.class);
    }

    @Test
    void shouldValidateLoanApplicationInformationIsMatchGuaranteeTypePaybackGuaranteeTypeIsNotSurety() {
        // Arrange
        when(loanApplicationRequestDTO.getPaybackGuaranteeType()).thenReturn(PaybackGuaranteeType.COLLATERAL);
        // Act
        suretyValidationCommand.validateLoanApplicationInformationIsMatchGuaranteeType(loanApplicationRequestDTO);
        // Assert
        verifyNoInteractions(suretyValidationService);
    }

    @Test
    void shouldValidateLoanApplicationInformationIsMatchGuaranteeTypePaybackGuaranteeTypeIsSurety() {
        Surety surety=mock(Surety.class);
       // Act
        suretyValidationService.validateFieldsAreNotNull(Mockito.any(Surety.class));
        // Assert
        verify(suretyValidationService, times(1)).validateFieldsAreNotNull(any());
    }
}