package com.findoutmyloan.application.facade;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.dto.CustomerLoanResponseDTO;
import com.findoutmyloan.application.loan.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;


public interface LoanFacade {
    CustomerLoanResponseDTO applyLoan(LoanApplicationRequestDTO loanApplicationRequestDTO);
  }
