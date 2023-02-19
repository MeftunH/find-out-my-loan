package com.findoutmyloan.application.facade.validation.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.errorMessage.LoanApplicationErrorMessage;
import com.findoutmyloan.application.facade.validation.service.LoanApplicationValidationService;
import com.findoutmyloan.application.general.exception.InformationMismatchException;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;

public class LoanApplicationValidationServiceImpl implements LoanApplicationValidationService {
    @Override
    public void validateIsLoanApplicationMatchWithPaybackGuaranteeType(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        boolean hasMatchSurety=loanApplicationRequestDTO.getSuretyName()!=null&&
                loanApplicationRequestDTO.getSuretySurname()!=null&&
                loanApplicationRequestDTO.getSuretyPhoneNumber()!=null&&
                !String.valueOf(loanApplicationRequestDTO.getSuretyIdentityNo()).equals("")&&
                loanApplicationRequestDTO.getSuretyBirthDate()!=null&&
                loanApplicationRequestDTO.getSuretyPersonType()!=null&&
                loanApplicationRequestDTO.getSuretyType()!=null;
        boolean hasMatchCollateral=loanApplicationRequestDTO.getCollateralType()!=null&&
                !String.valueOf(loanApplicationRequestDTO.getCollateralWorth()).equals("")&&
                loanApplicationRequestDTO.getPaybackGuaranteeType()==PaybackGuaranteeType.COLLATERAL;

        if (loanApplicationRequestDTO.getPaybackGuaranteeType()==PaybackGuaranteeType.SURETY) {
            if (!hasMatchSurety)
                throw new InformationMismatchException(LoanApplicationErrorMessage.APPLICATION_IS_NO_MATCH_WITH_PAYBACK_GUARANTEE_TYPE);
        }
        else if (loanApplicationRequestDTO.getPaybackGuaranteeType()==PaybackGuaranteeType.COLLATERAL) {
            if (!hasMatchCollateral)
                throw new InformationMismatchException(LoanApplicationErrorMessage.APPLICATION_IS_NO_MATCH_WITH_PAYBACK_GUARANTEE_TYPE);
        }
        else if (loanApplicationRequestDTO.getPaybackGuaranteeType()==PaybackGuaranteeType.ALL_OF_THEM) {
            if (!hasMatchSurety||!hasMatchCollateral)
                throw new InformationMismatchException(LoanApplicationErrorMessage.APPLICATION_IS_NO_MATCH_WITH_PAYBACK_GUARANTEE_TYPE);
        }
    }
}
