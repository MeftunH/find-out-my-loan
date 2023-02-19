package com.findoutmyloan.application.facade.validation.command.factory;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.validation.command.CollateralValidationCommand;
import com.findoutmyloan.application.collateral.validation.service.CollateralValidationService;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.errorMessage.LoanApplicationErrorMessage;
import com.findoutmyloan.application.facade.validation.command.LoanApplicationValidationCommand;
import com.findoutmyloan.application.general.exception.InformationMismatchException;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.surety.validation.command.SuretyValidationCommand;
import com.findoutmyloan.application.surety.validation.service.SuretyValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanApplicationValidationCommandFactory {
   private final SuretyValidationService suretyValidationService;
   private final CollateralValidationService collateralValidationService;
    public LoanApplicationValidationCommand createValidationCommand(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        if (loanApplicationRequestDTO.getPaybackGuaranteeType()==PaybackGuaranteeType.SURETY) {
            return new SuretyValidationCommand(suretyValidationService);
        }
        if (loanApplicationRequestDTO.getPaybackGuaranteeType()==PaybackGuaranteeType.COLLATERAL) {
            return new CollateralValidationCommand(collateralValidationService);
        }
        throw new InformationMismatchException(LoanApplicationErrorMessage.APPLICATION_IS_NO_MATCH_WITH_PAYBACK_GUARANTEE_TYPE);
    }
}
