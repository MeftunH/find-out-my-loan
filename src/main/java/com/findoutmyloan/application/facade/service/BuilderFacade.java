package com.findoutmyloan.application.facade.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;

public interface BuilderFacade {
    void invokeBuilder(LoanApplicationRequestDTO loanApplicationRequestDTO);

    CustomerCreditScoreRequestDTO getCustomerCreditScoreRequestDTO();
    SuretySaveRequestDTO getSuretySaveRequestDTO();
    CollateralSaveRequestDTO getCollateralSaveRequestDTO();
    CreditScoreRequestDTO getCreditScoreRequestDTO();
}
