package com.findoutmyloan.application.facade.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.facade.dto.CustomerLoanResponseDTO;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;


public interface LoanFacade {
    CustomerLoanResponseDTO applyLoan(LoanApplicationRequestDTO loanApplicationRequestDTO);
  }
