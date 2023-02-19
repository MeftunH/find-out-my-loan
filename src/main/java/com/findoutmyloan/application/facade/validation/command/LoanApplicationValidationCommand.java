package com.findoutmyloan.application.facade.validation.command;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;

public interface LoanApplicationValidationCommand {
    void validateLoanApplicationInformationIsMatchGuaranteeType(LoanApplicationRequestDTO loanApplicationRequestDTO);
}
