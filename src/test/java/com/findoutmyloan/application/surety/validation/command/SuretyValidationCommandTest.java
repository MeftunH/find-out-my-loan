package com.findoutmyloan.application.surety.validation.command;

import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.mapper.SuretyMapper;
import com.findoutmyloan.application.surety.validation.service.SuretyValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuretyValidationCommandTest {
    @Mock
    private Surety surety;
    @Mock
    private SuretyValidationService suretyValidationService;
    @InjectMocks
    @Spy
    private SuretyValidationCommand suretyValidationCommand;

    private LoanApplicationRequestDTO loanApplicationRequestDTO;

    @BeforeEach
    void setUp() {
        loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);
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
    void shouldNotThrowAnyExceptionValidateLoanApplicationInformationIsMatchGuaranteeTypeWithSurety() {
        // Create a mock instance of SuretyValidationService
        SuretyValidationService suretyValidationService=mock(SuretyValidationService.class);

        // Create a mock instance of LoanApplicationRequestDTO with PaybackGuaranteeType.SURETY
        LoanApplicationRequestDTO loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);

        doNothing().when(suretyValidationService).validateFieldsAreNotNull(Mockito.any(Surety.class));

        Assertions.assertDoesNotThrow(()->suretyValidationService.validateFieldsAreNotNull(SuretyMapper.INSTANCE.
                convertLoanApplicationRequestToSurety(loanApplicationRequestDTO)));
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