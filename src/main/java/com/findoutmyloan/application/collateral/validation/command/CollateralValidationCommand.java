package com.findoutmyloan.application.collateral.validation.command;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.mapper.CollateralMapper;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.validation.command.LoanApplicationValidationCommand;
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
            collateralValidationService.validateAreFieldsNonNull(CollateralMapper.INSTANCE.
                    convertLoanApplicationRequestToCollateral(loanApplicationRequestDTO));
        }
    }
}
