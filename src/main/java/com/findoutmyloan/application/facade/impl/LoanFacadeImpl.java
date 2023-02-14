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

@Service
@RequiredArgsConstructor
public class LoanFacadeImpl implements LoanFacade {
    private final LoanService loanService;
    private final CreditScoreApiService creditScoreApiService;
    private final EntityFacade entityFacade;
    private final CollateralService collateralService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Override
    public LoanDTO applyLoan(LoanApplicationRequestDTO loanApplicationRequestDTO) {


        CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO = CustomerCreditScoreRequestDTO.builder()
                .name(loanApplicationRequestDTO.getCustomerName())
                .surname(loanApplicationRequestDTO.getCustomerSurname())
                .birthDate(loanApplicationRequestDTO.getCustomerBirthDate())
                .identityNo(loanApplicationRequestDTO.getCustomerIdentityNo())
                .personType(loanApplicationRequestDTO.getCustomerPersonType())
                .phoneNumber(loanApplicationRequestDTO.getCustomerPhoneNumber())
                .monthlyIncome(loanApplicationRequestDTO.getCustomerMonthlyIncome())
                .build();
        SuretySaveRequestDTO suretySaveRequestDTO = SuretySaveRequestDTO.builder()
                .name(loanApplicationRequestDTO.getSuretyName())
                .surname(loanApplicationRequestDTO.getSuretySurname())
                .birthDate(loanApplicationRequestDTO.getSuretyBirthDate())
                .identityNo(loanApplicationRequestDTO.getSuretyIdentityNo())
                .personType(loanApplicationRequestDTO.getSuretyPersonType())
                .phoneNumber(loanApplicationRequestDTO.getSuretyPhoneNumber())
                .identityNo(loanApplicationRequestDTO.getSuretyIdentityNo())
                .toCustomerId(loanApplicationRequestDTO.getCustomerId())
                .build();

        CollateralSaveRequestDTO collateralSaveRequestDTO = CollateralSaveRequestDTO.builder()
                .collateralType(loanApplicationRequestDTO.getCollateralType())
                .customerId(loanApplicationRequestDTO.getCustomerId())
                .worth(loanApplicationRequestDTO.getCollateralWorth())
                .build();
        CreditScoreRequestDTO creditScoreRequestDTO = CreditScoreRequestDTO.builder()
                .customerCreditScoreRequestDTO(customerCreditScoreRequestDTO)
                .suretySaveRequestDTO(suretySaveRequestDTO)
                .collateralSaveRequestDTO(collateralSaveRequestDTO)
                .customerIdentityNo(loanApplicationRequestDTO.getCustomerIdentityNo())
                .build();
        CreditScoreResponseDTO creditScoreResponseDTO = creditScoreApiService.getCreditScore(creditScoreRequestDTO);
        entityFacade.saveEntity(suretySaveRequestDTO,collateralSaveRequestDTO);
        LoanSaveRequestDTO loanSaveRequestDTO=LoanMapper.INSTANCE.loanRequestFromCustomerDTOToLoanSaveRequestDTO(loanApplicationRequestDTO);
        float limitOfCustomer=loanService.calculateLimitOfCustomer(creditScoreResponseDTO.getCreditScore(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getMonthlyIncome());
        if (creditScoreRequestDTO.getCollateralSaveRequestDTO()!=null) {
            limitOfCustomer=collateralService.addCollateralWorthToLoanLimit(creditScoreRequestDTO.getCollateralSaveRequestDTO().getWorth(),
                    creditScoreResponseDTO.getCreditScore(),
                    creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getMonthlyIncome(),
                    limitOfCustomer
            );

        }
        loanSaveRequestDTO.setAmount(limitOfCustomer);
        if (!loanService.isSuitableForCalculate(creditScoreResponseDTO.getCreditScore())) {
            loanSaveRequestDTO.setResult(LoanResult.REJECTED);
        } else {
            loanSaveRequestDTO.setResult(LoanResult.APPROVED);
        }
        LoanDTO loanDTO=loanService.saveLoan(loanSaveRequestDTO);

        Customer customer=customerService.findCustomerByIdentityNoOrThrowException(creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getIdentityNo());
        customer.setCustomerLimit(limitOfCustomer);
        customerRepository.save(customer);
        System.out.println(creditScoreResponseDTO.getCreditScore());
        return loanDTO;
    }
}
