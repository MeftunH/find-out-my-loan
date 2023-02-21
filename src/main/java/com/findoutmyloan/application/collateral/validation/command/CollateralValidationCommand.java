package com.findoutmyloan.application.collateral.validation.command;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.entity.Collateral;
import com.findoutmyloan.application.collateral.enums.CollateralErrorMessage;
import com.findoutmyloan.application.collateral.mapper.CollateralMapper;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.errorMessage.LoanApplicationErrorMessage;
import com.findoutmyloan.application.facade.validation.command.LoanApplicationValidationCommand;
import com.findoutmyloan.application.general.exception.InformationMismatchException;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("CollateralValidationCommand")
public class CollateralValidationCommand implements LoanApplicationValidationCommand {
    private final CollateralValidationService collateralValidationService;
    @Override
    public void validateLoanApplicationInformationIsMatchGuaranteeType(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        if (loanApplicationRequestDTO.getPaybackGuaranteeType()==PaybackGuaranteeType.COLLATERAL) {
            Collateral collateral=CollateralMapper.INSTANCE.
                    convertLoanApplicationRequestToCollateral(loanApplicationRequestDTO);
            collateralValidationService.validateAreFieldsNonNull(collateral);
        } else {
            throw new InformationMismatchException(LoanApplicationErrorMessage.APPLICATION_IS_NO_MATCH_WITH_PAYBACK_GUARANTEE_TYPE);
        }
    }
}
