package com.findoutmyloan.application.facade.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.service.BuilderFacade;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.security.service.AuthenticationService;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BuilderFacadeImpl implements BuilderFacade {

    private final AuthenticationService authenticationService;
    private CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO;
    private SuretySaveRequestDTO suretySaveRequestDTO;
    private CollateralSaveRequestDTO collateralSaveRequestDTO;
    private CreditScoreRequestDTO creditScoreRequestDTO;

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
        if (authenticationService.getCurrentCustomer()==null)
            throw new GeneralBusinessException(CustomerErrorMessage.CUSTOMER_NOT_FOUND);

        customerCreditScoreRequestDTO=CustomerCreditScoreRequestDTO.builder()
                .name(authenticationService.getCurrentCustomer().getName())
                .surname(authenticationService.getCurrentCustomer().getSurname())
                .birthDate(authenticationService.getCurrentCustomer().getBirthDate())
                .identityNo(authenticationService.getCurrentCustomer().getIdentityNo())
                .personType(authenticationService.getCurrentCustomer().getPersonType())
                .phoneNumber(authenticationService.getCurrentCustomer().getPhoneNumber())
                .monthlyIncome(authenticationService.getCurrentCustomer().getMonthlyIncome())
                .build();
        suretySaveRequestDTO=SuretySaveRequestDTO.builder()
                .name(loanApplicationRequestDTO.getSuretyName())
                .surname(loanApplicationRequestDTO.getSuretySurname())
                .birthDate(loanApplicationRequestDTO.getSuretyBirthDate())
                .identityNo(loanApplicationRequestDTO.getSuretyIdentityNo())
                .personType(loanApplicationRequestDTO.getSuretyPersonType())
                .suretyType(loanApplicationRequestDTO.getSuretyType())
                .phoneNumber(loanApplicationRequestDTO.getSuretyPhoneNumber())
                .identityNo(loanApplicationRequestDTO.getSuretyIdentityNo())
                .build();

        collateralSaveRequestDTO=CollateralSaveRequestDTO.builder()
                .collateralType(loanApplicationRequestDTO.getCollateralType())
                .worth(loanApplicationRequestDTO.getCollateralWorth())
                .build();
        creditScoreRequestDTO=CreditScoreRequestDTO.builder()
                .customerCreditScoreRequestDTO(customerCreditScoreRequestDTO)
                .suretySaveRequestDTO(suretySaveRequestDTO)
                .collateralSaveRequestDTO(collateralSaveRequestDTO)
                .customerIdentityNo(authenticationService.getCurrentCustomer().getIdentityNo())
                .build();
    }
}
