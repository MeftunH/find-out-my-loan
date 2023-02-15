package com.findoutmyloan.application.facade.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.facade.BuilderFacade;
import com.findoutmyloan.application.loan.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuilderFacadeImpl implements BuilderFacade {

    public CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO;
    public SuretySaveRequestDTO suretySaveRequestDTO;
    public CollateralSaveRequestDTO collateralSaveRequestDTO;
    public CreditScoreRequestDTO creditScoreRequestDTO;

    public CustomerCreditScoreRequestDTO getCustomerCreditScoreRequestDTO() {
        return customerCreditScoreRequestDTO;
    }

    public SuretySaveRequestDTO getSuretySaveRequestDTO() {
        return suretySaveRequestDTO;
    }

    public CollateralSaveRequestDTO getCollateralSaveRequestDTO() {
        return collateralSaveRequestDTO;
    }

    public CreditScoreRequestDTO getCreditScoreRequestDTO() {
        return creditScoreRequestDTO;
    }

    public void invokeBuilder(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        customerCreditScoreRequestDTO=CustomerCreditScoreRequestDTO.builder()
                .name(loanApplicationRequestDTO.getCustomerName())
                .surname(loanApplicationRequestDTO.getCustomerSurname())
                .birthDate(loanApplicationRequestDTO.getCustomerBirthDate())
                .identityNo(loanApplicationRequestDTO.getCustomerIdentityNo())
                .personType(loanApplicationRequestDTO.getCustomerPersonType())
                .phoneNumber(loanApplicationRequestDTO.getCustomerPhoneNumber())
                .monthlyIncome(loanApplicationRequestDTO.getCustomerMonthlyIncome())
                .build();
        suretySaveRequestDTO=SuretySaveRequestDTO.builder()
                .name(loanApplicationRequestDTO.getSuretyName())
                .surname(loanApplicationRequestDTO.getSuretySurname())
                .birthDate(loanApplicationRequestDTO.getSuretyBirthDate())
                .identityNo(loanApplicationRequestDTO.getSuretyIdentityNo())
                .personType(loanApplicationRequestDTO.getSuretyPersonType())
                .phoneNumber(loanApplicationRequestDTO.getSuretyPhoneNumber())
                .identityNo(loanApplicationRequestDTO.getSuretyIdentityNo())
                .toCustomerId(loanApplicationRequestDTO.getCustomerId())
                .build();

        collateralSaveRequestDTO=CollateralSaveRequestDTO.builder()
                .collateralType(loanApplicationRequestDTO.getCollateralType())
                .customerId(loanApplicationRequestDTO.getCustomerId())
                .worth(loanApplicationRequestDTO.getCollateralWorth())
                .build();
        creditScoreRequestDTO=CreditScoreRequestDTO.builder()
                .customerCreditScoreRequestDTO(customerCreditScoreRequestDTO)
                .suretySaveRequestDTO(suretySaveRequestDTO)
                .collateralSaveRequestDTO(collateralSaveRequestDTO)
                .customerIdentityNo(loanApplicationRequestDTO.getCustomerIdentityNo())
                .build();
    }
}