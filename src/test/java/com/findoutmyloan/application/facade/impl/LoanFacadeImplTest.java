package com.findoutmyloan.application.facade.impl;

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.collateral.service.CollateralService;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.creditscore.dto.CreditScoreResponseDTO;
import com.findoutmyloan.application.creditscore.service.CreditScoreApiService;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.facade.BuilderFacade;
import com.findoutmyloan.application.facade.EntityFacade;
import com.findoutmyloan.application.facade.LoanFacade;
import com.findoutmyloan.application.facade.dto.CustomerLoanResponseDTO;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.enums.PaybackGuaranteeType;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class LoanFacadeImplTest {
    @MockBean
    private LoanService loanService;
    @MockBean
    private CreditScoreApiService creditScoreApiService;
    @MockBean
    private EntityFacade entityFacade;
    @MockBean
    private BuilderFacade builderFacade;
    @MockBean
    private CollateralService collateralService;
    @MockBean
    private CustomerService customerService;

    @Autowired
    private LoanFacade loanFacade;

    @Test
    @DisplayName("Should save the loan when the credit score is not suitable for calculate")
    void applyLoanWhenCreditScoreIsNotSuitableForCalculateThenSaveLoan() {
        LoanApplicationRequestDTO loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);
        CreditScoreRequestDTO creditScoreRequestDTO=mock(CreditScoreRequestDTO.class);
        CreditScoreResponseDTO creditScoreResponseDTO=mock(CreditScoreResponseDTO.class);
        creditScoreResponseDTO.setCreditScore(0);
        LoanSaveRequestDTO loanSaveRequestDTO=mock(LoanSaveRequestDTO.class);
        LoanDTO loanDTO=mock(LoanDTO.class);
        loanDTO.setAmount(0);
        CustomerLoanResponseDTO customerLoanResponseDTO=mock(CustomerLoanResponseDTO.class);
        customerLoanResponseDTO.setAmount(0);
        customerLoanResponseDTO.setCustomerLimit(0);
        customerLoanResponseDTO.setPaybackGuaranteeType(PaybackGuaranteeType.SURETY);
        customerLoanResponseDTO.setResult(LoanResult.REJECTED);

        doNothing().when(builderFacade).invokeBuilder(loanApplicationRequestDTO);
        when(builderFacade.getCreditScoreRequestDTO()).thenReturn(creditScoreRequestDTO);
        when(creditScoreApiService.getCreditScore(creditScoreRequestDTO)).thenReturn(creditScoreResponseDTO);
        when(loanService.calculateLimitOfLoan(creditScoreResponseDTO.getCreditScore(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getMonthlyIncome())).thenReturn(0f);
        when(loanService.isSuitableForCalculate(creditScoreResponseDTO.getCreditScore())).thenReturn(false);
        when(loanService.saveLoan(loanSaveRequestDTO)).thenReturn(loanDTO);
        when(customerService.getLimitOfCustomer(loanApplicationRequestDTO, 0f)).thenReturn(0f);

        CustomerLoanResponseDTO result=loanFacade.applyLoan(loanApplicationRequestDTO);

        assertEquals(customerLoanResponseDTO, result);
    }

    @Test
    @DisplayName("Should save the loan when the credit score is suitable for calculate")
    void applyLoanWhenCreditScoreIsSuitableForCalculateThenSaveLoan() {
        LoanApplicationRequestDTO loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);
        CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO=mock(CustomerCreditScoreRequestDTO.class);
        SuretySaveRequestDTO suretySaveRequestDTO=mock(SuretySaveRequestDTO.class);
        CollateralSaveRequestDTO collateralSaveRequestDTO=mock(CollateralSaveRequestDTO.class);
        CreditScoreRequestDTO creditScoreRequestDTO=mock(CreditScoreRequestDTO.class);
        CreditScoreResponseDTO creditScoreResponseDTO=mock(CreditScoreResponseDTO.class);
        LoanSaveRequestDTO loanSaveRequestDTO=mock(LoanSaveRequestDTO.class);
        LoanDTO loanDTO=mock(LoanDTO.class);

        doNothing().when(builderFacade).invokeBuilder(loanApplicationRequestDTO);
        when(builderFacade.getCustomerCreditScoreRequestDTO()).thenReturn(customerCreditScoreRequestDTO);
        when(builderFacade.getSuretySaveRequestDTO()).thenReturn(suretySaveRequestDTO);
        when(builderFacade.getCollateralSaveRequestDTO()).thenReturn(collateralSaveRequestDTO);
        when(builderFacade.getCreditScoreRequestDTO()).thenReturn(creditScoreRequestDTO);
        when(creditScoreApiService.getCreditScore(creditScoreRequestDTO)).thenReturn(creditScoreResponseDTO);

        doNothing().when(builderFacade).invokeBuilder(loanApplicationRequestDTO);

        when(LoanMapper.INSTANCE.loanRequestFromCustomerDTOToLoanSaveRequestDTO(loanApplicationRequestDTO)).thenReturn(loanSaveRequestDTO);

        float limitOfLoan=100;
        when(loanService.calculateLimitOfLoan(creditScoreResponseDTO.getCreditScore(), customerCreditScoreRequestDTO.getMonthlyIncome())).thenReturn(limitOfLoan);

        when(collateralService.addCollateralWorthToLoanLimit(collateralSaveRequestDTO.getWorth(), creditScoreResponseDTO.getCreditScore(), customerCreditScoreRequestDTO.getMonthlyIncome(), limitOfLoan)).thenReturn(limitOfLoan);

        when(loanService.isSuitableForCalculate(creditScoreResponseDTO.getCreditScore())).thenReturn(true);

        when(loanService.saveLoan(loanSaveRequestDTO)).thenReturn(loanDTO);

        float limitOfCustomer=100;
        when(customerService.getLimitOfCustomer(loanApplicationRequestDTO, limitOfLoan)).thenReturn(limitOfCustomer);

        CustomerLoanResponseDTO customerLoanResponseDTOResult=loanFacade.applyLoan(loanApplicationRequestDTO);

        assertEquals(limitOfCustomer, customerLoanResponseDTOResult.getCustomerLimit());
    }

    @Test
    @DisplayName("Should save the loan when the collateral worth is null")
    void applyLoanWhenCollateralWorthIsNullThenSaveLoan() {
        LoanApplicationRequestDTO loanApplicationRequestDTO=LoanApplicationRequestDTO.builder().customerName("customerName").customerSurname("customerSurname").customerIdentityNo(123456789).customerBirthDate(new Date()).customerPhoneNumber("customerPhoneNumber").customerPersonType(PersonType.CUSTOMER).customerMonthlyIncome(1000).suretyName("suretyName").suretySurname("suretySurname").suretyIdentityNo(123456789).suretyBirthDate(new Date()).suretyPhoneNumber("suretyPhoneNumber").suretyPersonType(PersonType.SURETY).collateralType(CollateralType.CAR).collateralWorth(0).paybackGuaranteeType(PaybackGuaranteeType.SURETY).build();

        CreditScoreRequestDTO creditScoreRequestDTO=CreditScoreRequestDTO.builder().customerIdentityNo(123456789).customerCreditScoreRequestDTO(CustomerCreditScoreRequestDTO.builder().name("customerName").surname("customerSurname").identityNo(123456789).birthDate(new Date()).phoneNumber("customerPhoneNumber").personType(PersonType.CUSTOMER).monthlyIncome(1000).build()).suretySaveRequestDTO(SuretySaveRequestDTO.builder().name("suretyName").surname("suretySurname").identityNo(123456789).birthDate(new Date()).phoneNumber("suretyPhoneNumber").personType(PersonType.SURETY).build()).collateralSaveRequestDTO(CollateralSaveRequestDTO.builder().collateralType(CollateralType.CAR).worth(0).build()).paybackGuaranteeType(PaybackGuaranteeType.SURETY).build();


        LoanSaveRequestDTO loanSaveRequestDTO=LoanSaveRequestDTO.builder().paybackGuaranteeType(PaybackGuaranteeType.SURETY).amount(1000).result(LoanResult.APPROVED).creditScore(100).build();

        LoanDTO loanDTO=LoanDTO.builder().paybackGuaranteeType(PaybackGuaranteeType.SURETY).amount(1000).result(LoanResult.APPROVED).build();

        CustomerLoanResponseDTO customerLoanResponseDTO=CustomerLoanResponseDTO.builder().paybackGuaranteeType(PaybackGuaranteeType.SURETY).amount(1000).result(LoanResult.APPROVED).customerLimit(1000).build();

        doNothing().when(builderFacade).invokeBuilder(loanApplicationRequestDTO);
        when(loanService.saveLoan(loanSaveRequestDTO)).thenReturn(loanDTO);
        when(customerService.getLimitOfCustomer(loanApplicationRequestDTO, 1000)).thenReturn(1000f);

        CustomerLoanResponseDTO result=loanFacade.applyLoan(loanApplicationRequestDTO);

        assertEquals(customerLoanResponseDTO, result);

        verify(builderFacade, times(1)).invokeBuilder(loanApplicationRequestDTO);
        verifyNoMoreInteractions(builderFacade);

        verify(creditScoreApiService, times(1)).getCreditScore(creditScoreRequestDTO);
        verifyNoMoreInteractions(creditScoreApiService);

        verifyNoInteractions(entityFacade);

        verifyNoInteractions(collateralService);

        verifyNoInteractions(customerService);

        verifyNoInteractions(loanService);
    }

    @Test
    @DisplayName("Should save the loan when the collateral worth is not null")
    void applyLoanWhenCollateralWorthIsNotNullThenSaveLoan() {
        LoanApplicationRequestDTO loanApplicationRequestDTO=LoanApplicationRequestDTO.builder().customerName("customerName").customerSurname("customerSurname").customerIdentityNo(123456789).customerBirthDate(new Date()).customerPhoneNumber("customerPhoneNumber").customerPersonType(PersonType.CUSTOMER).customerMonthlyIncome(1000).suretyName("suretyName").suretySurname("suretySurname").suretyIdentityNo(123456789).suretyBirthDate(new Date()).suretyPhoneNumber("suretyPhoneNumber").suretyPersonType(PersonType.SURETY).collateralType(CollateralType.CAR).collateralWorth(1000).paybackGuaranteeType(PaybackGuaranteeType.COLLATERAL).build();

        CreditScoreRequestDTO creditScoreRequestDTO=CreditScoreRequestDTO.builder().customerIdentityNo(123456789).customerCreditScoreRequestDTO(CustomerCreditScoreRequestDTO.builder().name("customerName").surname("customerSurname").identityNo(123456789).birthDate(new Date()).phoneNumber("customerPhoneNumber").personType(PersonType.CUSTOMER).monthlyIncome(1000).build()).suretySaveRequestDTO(SuretySaveRequestDTO.builder().name("suretyName").surname("suretySurname").identityNo(123456789).birthDate(new Date()).phoneNumber("suretyPhoneNumber").personType(PersonType.SURETY).build()).collateralSaveRequestDTO(CollateralSaveRequestDTO.builder().collateralType(CollateralType.CAR).worth(1000).build()).paybackGuaranteeType(PaybackGuaranteeType.COLLATERAL).build();


        LoanSaveRequestDTO loanSaveRequestDTO=LoanSaveRequestDTO.builder().paybackGuaranteeType(PaybackGuaranteeType.COLLATERAL).amount(1000).result(LoanResult.APPROVED).creditScore(100).build();


        CustomerLoanResponseDTO customerLoanResponseDTO=CustomerLoanResponseDTO.builder().paybackGuaranteeType(PaybackGuaranteeType.COLLATERAL).amount(1000).result(LoanResult.APPROVED).customerLimit(1000).build();

        doNothing().when(builderFacade).invokeBuilder(loanApplicationRequestDTO);
        when(customerService.getLimitOfCustomer(loanApplicationRequestDTO, 1000)).thenReturn(1000f);

        CustomerLoanResponseDTO result=loanFacade.applyLoan(loanApplicationRequestDTO);

        assertEquals(customerLoanResponseDTO, result);

        verify(builderFacade, times(1)).invokeBuilder(loanApplicationRequestDTO);
        verifyNoMoreInteractions(builderFacade);

        verify(creditScoreApiService, times(1)).getCreditScore(creditScoreRequestDTO);
        verifyNoMoreInteractions(creditScoreApiService);

        verifyNoInteractions(entityFacade);

        verifyNoInteractions(collateralService);

        verifyNoInteractions(customerService);

        verifyNoInteractions(loanService);
    }
}