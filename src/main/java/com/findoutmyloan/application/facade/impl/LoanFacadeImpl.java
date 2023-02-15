package com.findoutmyloan.application.facade.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreResponseDTO;
import com.findoutmyloan.application.creditscore.service.CreditScoreApiService;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.facade.BuilderFacade;
import com.findoutmyloan.application.facade.EntityFacade;
import com.findoutmyloan.application.facade.LoanFacade;
import com.findoutmyloan.application.loan.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.person.dto.PersonDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class LoanFacadeImpl implements LoanFacade {
    private final LoanService loanService;
    private final CreditScoreApiService creditScoreApiService;
    private final EntityFacade entityFacade;
    private final BuilderFacade builderFacade;
    private final CollateralService collateralService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Override
    public LoanDTO applyLoan(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        builderFacade.invokeBuilder(loanApplicationRequestDTO);

        CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO = builderFacade.getCustomerCreditScoreRequestDTO();
        SuretySaveRequestDTO suretySaveRequestDTO = builderFacade.getSuretySaveRequestDTO();
        CollateralSaveRequestDTO collateralSaveRequestDTO = builderFacade.getCollateralSaveRequestDTO();
        CreditScoreRequestDTO creditScoreRequestDTO = builderFacade.getCreditScoreRequestDTO();
        CreditScoreResponseDTO creditScoreResponseDTO = creditScoreApiService.getCreditScore(creditScoreRequestDTO);

        entityFacade.saveEntity(suretySaveRequestDTO,collateralSaveRequestDTO);

        LoanSaveRequestDTO loanSaveRequestDTO=LoanMapper.INSTANCE.loanRequestFromCustomerDTOToLoanSaveRequestDTO(loanApplicationRequestDTO);
        float limitOfCustomer=loanService.calculateLimitOfCustomer(creditScoreResponseDTO.getCreditScore(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getMonthlyIncome());
        limitOfCustomer=getLimitOfCustomer(creditScoreRequestDTO, creditScoreResponseDTO, limitOfCustomer);
        LoanDTO loanDTO=setLoanDTO(creditScoreResponseDTO, loanSaveRequestDTO, limitOfCustomer);
        Customer customer=customerService.findCustomerByIdentityNoOrThrowException(creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getIdentityNo());
        customer.setCustomerLimit(limitOfCustomer);
        customerRepository.save(customer);

        return loanDTO;
    }

    private float getLimitOfCustomer(CreditScoreRequestDTO creditScoreRequestDTO, CreditScoreResponseDTO creditScoreResponseDTO, float limitOfCustomer) {
        if (creditScoreRequestDTO.getCollateralSaveRequestDTO()!=null) {
            limitOfCustomer=collateralService.addCollateralWorthToLoanLimit(creditScoreRequestDTO.getCollateralSaveRequestDTO().getWorth(),
                    creditScoreResponseDTO.getCreditScore(),
                    creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getMonthlyIncome(),
                    limitOfCustomer
            );
        }
        return limitOfCustomer;
    }

    private LoanDTO setLoanDTO(CreditScoreResponseDTO creditScoreResponseDTO, LoanSaveRequestDTO loanSaveRequestDTO, float limitOfCustomer) {
        loanSaveRequestDTO.setAmount(limitOfCustomer);
        if (!loanService.isSuitableForCalculate(creditScoreResponseDTO.getCreditScore())) {
            loanSaveRequestDTO.setResult(LoanResult.REJECTED);
        } else {
            loanSaveRequestDTO.setResult(LoanResult.APPROVED);
        }
        return loanService.saveLoan(loanSaveRequestDTO);
    }
}
