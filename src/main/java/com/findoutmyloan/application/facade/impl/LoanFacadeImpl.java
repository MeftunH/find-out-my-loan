package com.findoutmyloan.application.facade.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreResponseDTO;
import com.findoutmyloan.application.creditscore.service.CreditScoreApiService;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.facade.LoanFacade;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.service.SuretyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanFacadeImpl implements LoanFacade {
    private final LoanService loanService;
    private final CreditScoreApiService creditScoreApiService;
    private final SuretyService suretyService;

    @Override
    public LoanDTO applyForLoan(CreditScoreRequestDTO creditScoreRequestDTO,LoanSaveRequestDTO loanSaveRequestDTO) {
         CreditScoreResponseDTO creditScoreResponseDTO=creditScoreApiService.getCreditScore(creditScoreRequestDTO);
         LoanSaveRequestDTO loanSaveRequestDTO=creditScoreRequestDTO.getLoanSaveRequestDTO();
         loanService.saveLoan(loanSaveRequestDTO);
         if (creditScoreRequestDTO.getSuretySaveRequestDTO()!=null) {
             SuretySaveRequestDTO suretySaveRequestDTO=creditScoreRequestDTO.getSuretySaveRequestDTO();
             suretyService.saveSurety(suretySaveRequestDTO);
         }




        return null;
    }
}
