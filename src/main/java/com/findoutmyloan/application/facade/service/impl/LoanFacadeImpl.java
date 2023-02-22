package com.findoutmyloan.application.facade.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreResponseDTO;
import com.findoutmyloan.application.creditscore.service.CreditScoreApiService;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.facade.dto.CustomerLoanResponseDTO;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.facade.service.BuilderFacade;
import com.findoutmyloan.application.facade.service.EntityFacade;
import com.findoutmyloan.application.facade.service.LoanFacade;
import com.findoutmyloan.application.facade.validation.command.LoanApplicationValidationCommand;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class LoanFacadeImpl implements LoanFacade {
    private final LoanService loanService;
    private final CreditScoreApiService creditScoreApiService;
    private final EntityFacade entityFacade;
    private final BuilderFacade builderFacade;
    private final CollateralService collateralService;
    private final CustomerService customerService;
    private final LoanApplicationValidationCommand loanApplicationValidationCommand;

    @Override
    public CustomerLoanResponseDTO applyLoan(LoanApplicationRequestDTO loanApplicationRequestDTO) {

        loanApplicationValidationCommand.validateLoanApplicationInformationIsMatchGuaranteeType(loanApplicationRequestDTO);

        builderFacade.invokeBuilder(loanApplicationRequestDTO);

        CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO=builderFacade.getCustomerCreditScoreRequestDTO();
        SuretySaveRequestDTO suretySaveRequestDTO=builderFacade.getSuretySaveRequestDTO();
        CollateralSaveRequestDTO collateralSaveRequestDTO=builderFacade.getCollateralSaveRequestDTO();
        CreditScoreRequestDTO creditScoreRequestDTO=builderFacade.getCreditScoreRequestDTO();
        CreditScoreResponseDTO creditScoreResponseDTO=creditScoreApiService.getCreditScore(creditScoreRequestDTO);

        entityFacade.saveEntity(suretySaveRequestDTO, collateralSaveRequestDTO);

        LoanSaveRequestDTO loanSaveRequestDTO=LoanMapper.INSTANCE.loanRequestFromCustomerDTOToLoanSaveRequestDTO(loanApplicationRequestDTO);
        float limitOfLoan=getTotalLimitOfLoan(creditScoreRequestDTO, creditScoreResponseDTO);
        LoanDTO loanDTO=setLoanDTO(creditScoreResponseDTO, loanSaveRequestDTO, limitOfLoan);
        return getCustomerLoanResponseDTO(limitOfLoan, loanDTO);
    }

    private CustomerLoanResponseDTO getCustomerLoanResponseDTO(float limitOfLoan, LoanDTO loanDTO) {
        float limitOfCustomer=customerService.getUpdatedLimitOfCustomer(limitOfLoan);

        CustomerLoanResponseDTO customerLoanResponseDTO=LoanMapper.INSTANCE.convertLoanDTOToCustomerLoanResponseDTO(loanDTO);
        customerLoanResponseDTO.setCustomerLimit(limitOfCustomer);
        customerLoanResponseDTO.setAmount(limitOfLoan);
        return customerLoanResponseDTO;
    }

    private float getTotalLimitOfLoan(CreditScoreRequestDTO creditScoreRequestDTO, CreditScoreResponseDTO creditScoreResponseDTO) {
        float limitOfLoan=loanService.calculateLimitOfLoan(creditScoreResponseDTO.getCreditScore(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getMonthlyIncome());
        if (creditScoreRequestDTO.getCollateralSaveRequestDTO().getCollateralType()!=null) {
            limitOfLoan=addCollateralWorthToLoanLimit(creditScoreRequestDTO, creditScoreResponseDTO, limitOfLoan);
        }
        return limitOfLoan;
    }

    private float addCollateralWorthToLoanLimit(CreditScoreRequestDTO creditScoreRequestDTO, CreditScoreResponseDTO creditScoreResponseDTO, float limitOfLoan) {
        if (creditScoreRequestDTO.getCollateralSaveRequestDTO()!=null) {
            limitOfLoan=collateralService.addCollateralWorthToLoanLimit(creditScoreRequestDTO.getCollateralSaveRequestDTO().getWorth(),
                    creditScoreResponseDTO.getCreditScore(),
                    creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getMonthlyIncome(),
                    limitOfLoan
            );
        }
        return limitOfLoan;
    }

    private LoanDTO setLoanDTO(CreditScoreResponseDTO creditScoreResponseDTO, LoanSaveRequestDTO loanSaveRequestDTO, float limitOfLoan) {
        loanSaveRequestDTO.setAmount(limitOfLoan);
        if (!loanService.isSuitableForCalculate(creditScoreResponseDTO.getCreditScore())) {
            loanSaveRequestDTO.setResult(LoanResult.REJECTED);
        } else {
            loanSaveRequestDTO.setResult(LoanResult.APPROVED);
        }
        return loanService.saveLoan(loanSaveRequestDTO);
    }
}
