package com.findoutmyloan.application.facade;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;

public interface LoanFacade {
    public LoanDTO applyForLoan(CreditScoreRequestDTO creditScoreRequestDTO);
}
