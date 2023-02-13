package com.findoutmyloan.application.loan.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;

public interface LoanService {
    LoanDTO saveCredit(LoanSaveRequestDTO loanSaveRequestDTO);
}
