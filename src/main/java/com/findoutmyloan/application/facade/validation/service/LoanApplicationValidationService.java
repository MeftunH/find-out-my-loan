package com.findoutmyloan.application.facade.validation.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;

public interface LoanApplicationValidationService {
    void validateIsLoanApplicationMatchWithPaybackGuaranteeType(LoanApplicationRequestDTO loanApplicationRequestDTO);

}
