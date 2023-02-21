package com.findoutmyloan.application.collateral.validation.command;

import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.collateral.mapper.CollateralMapper;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.errorMessage.LoanApplicationErrorMessage;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.InformationMismatchException;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) //allow unnecessary stubs for same time injectMock and spy
class CollateralValidationCommandTest {
    @Mock
    private CollateralValidationService collateralValidationService;
    @Spy
    @InjectMocks
    private CollateralValidationCommand collateralValidationCommand;


    @Test
    void shouldDoesNotThrowExceptionValidateLoanApplicationInformationIsMatchGuaranteeTypeWhenGuaranteeTypeIsCollateral() {
        // Given
        LoanApplicationRequestDTO loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);

        // When
        when(loanApplicationRequestDTO.getPaybackGuaranteeType()).thenReturn(PaybackGuaranteeType.COLLATERAL);
        loanApplicationRequestDTO.setPaybackGuaranteeType(PaybackGuaranteeType.COLLATERAL);
        when(loanApplicationRequestDTO.getCollateralWorth()).thenReturn(1000.0f);

        collateralValidationCommand.validateLoanApplicationInformationIsMatchGuaranteeType(loanApplicationRequestDTO);

        // Then
        assertDoesNotThrow(()->collateralValidationCommand.validateLoanApplicationInformationIsMatchGuaranteeType(loanApplicationRequestDTO));
    }

    @Test
    void shouldThrowExceptionValidateLoanApplicationInformationIsMatchGuaranteeTypeWhenGuaranteeTypeIsNotCollateral() {
        // Then
       doThrow(new InformationMismatchException(LoanApplicationErrorMessage.APPLICATION_IS_NO_MATCH_WITH_PAYBACK_GUARANTEE_TYPE))
               .when(collateralValidationCommand).validateLoanApplicationInformationIsMatchGuaranteeType(any());
    }

    @Test
    public void validateLoanApplicationInformationIsMatchGuaranteeType_withNonCollateral_shouldNotCallCollateralValidationService() {
        // Given
        LoanApplicationRequestDTO loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);
        loanApplicationRequestDTO.setPaybackGuaranteeType(PaybackGuaranteeType.COLLATERAL);

        // When
        collateralValidationCommand.validateLoanApplicationInformationIsMatchGuaranteeType(loanApplicationRequestDTO);

        // Then
        verifyNoInteractions(collateralValidationService);
    }

    @Test
    public void validateLoanApplicationInformationIsMatchGuaranteeType_withCollateralValidationServiceError_shouldThrowException() {
        // Given
        LoanApplicationRequestDTO loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);
        loanApplicationRequestDTO.setPaybackGuaranteeType(PaybackGuaranteeType.COLLATERAL);

        Collateral collateral=new Collateral();
//        when(collateralValidationService.validateAreFieldsNonNull(collateral)).thenThrow(new ValidationException());

        when(CollateralMapper.INSTANCE.convertLoanApplicationRequestToCollateral(loanApplicationRequestDTO))
                .thenReturn(collateral);

        // When/Then
        assertThrows(ValidationException.class,
                ()->collateralValidationCommand.validateLoanApplicationInformationIsMatchGuaranteeType(loanApplicationRequestDTO));
    }
}