package com.findoutmyloan.application.collateral.validation.command;

import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.collateral.mapper.CollateralMapper;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.InformationMismatchException;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CollateralValidationCommandTest {
    @Mock
    private CollateralValidationService collateralValidationService;
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
        // Given
        LoanApplicationRequestDTO loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);

        collateralValidationCommand.validateLoanApplicationInformationIsMatchGuaranteeType(loanApplicationRequestDTO);

        // Then
        assertThrows(GeneralBusinessException.class,()->collateralValidationCommand.validateLoanApplicationInformationIsMatchGuaranteeType(loanApplicationRequestDTO));
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