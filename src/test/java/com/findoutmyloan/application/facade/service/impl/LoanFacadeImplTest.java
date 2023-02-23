package com.findoutmyloan.application.facade.service.impl;

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.enums.CollateralType;
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
import com.findoutmyloan.application.facade.validation.command.LoanApplicationValidationCommand;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanFacadeImplTest {

    @Mock
    private LoanService loanService;
    @Mock
    private CreditScoreApiService creditScoreApiService;
    @Mock
    private EntityFacade entityFacade;
    @Mock
    private BuilderFacade builderFacade;
    @Mock
    private CollateralService collateralService;
    @Mock
    private CustomerService customerService;
    @Mock
    private LoanApplicationValidationCommand loanApplicationValidationCommand;

    @InjectMocks
    private LoanFacadeImpl loanFacade;

    private LoanSaveRequestDTO loanSaveRequestDTO;
    private CustomerLoanResponseDTO customerLoanResponseDTO;

    @BeforeEach
    void setUp() {
        loanSaveRequestDTO=mock(LoanSaveRequestDTO.class);
        customerLoanResponseDTO=mock(CustomerLoanResponseDTO.class);
    }

    @Test
    void shouldApplyLoan() {
        // Setup
        LoanApplicationRequestDTO requestDTO=mock(LoanApplicationRequestDTO.class);
        CreditScoreResponseDTO creditScoreResponseDTO=mock(CreditScoreResponseDTO.class);
        CreditScoreRequestDTO creditScoreRequestDTO=mock(CreditScoreRequestDTO.class);
        CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO=mock(CustomerCreditScoreRequestDTO.class);
        SuretySaveRequestDTO suretySaveRequestDTO=mock(SuretySaveRequestDTO.class);
        CollateralSaveRequestDTO collateralSaveRequestDTO=mock(CollateralSaveRequestDTO.class);
        LoanDTO loanDTO=mock(LoanDTO.class);

        when(builderFacade.getCustomerCreditScoreRequestDTO()).thenReturn(customerCreditScoreRequestDTO);
        when(builderFacade.getSuretySaveRequestDTO()).thenReturn(suretySaveRequestDTO);
        when(builderFacade.getCollateralSaveRequestDTO()).thenReturn(collateralSaveRequestDTO);
        when(builderFacade.getCreditScoreRequestDTO()).thenReturn(creditScoreRequestDTO);
        when(creditScoreApiService.getCreditScore(any(CreditScoreRequestDTO.class))).thenReturn(creditScoreResponseDTO);
        when(customerService.getUpdatedLimitOfCustomer(anyFloat())).thenReturn(10000f);
        when(loanService.calculateLimitOfLoan(anyInt(), anyFloat())).thenReturn(5000f);
        when(collateralService.addCollateralWorthToLoanLimit(anyFloat(), anyInt(), anyFloat(), anyFloat())).thenReturn(6000f);
        when(loanService.isSuitableForCalculate(anyInt())).thenReturn(true);
        when(loanService.saveLoan(any(LoanSaveRequestDTO.class))).thenReturn(loanDTO);

        doReturn(customerCreditScoreRequestDTO).when(creditScoreRequestDTO).getCustomerCreditScoreRequestDTO();
        doReturn(CollateralType.CAR).when(collateralSaveRequestDTO).getCollateralType();
        doReturn(collateralSaveRequestDTO).when(creditScoreRequestDTO).getCollateralSaveRequestDTO();

        // Execution
        CustomerLoanResponseDTO responseDTO=loanFacade.applyLoan(requestDTO);

        // Verification
        assertNotNull(responseDTO);
        assertNotEquals(10000f, responseDTO.getAmount(), 0.01f);
        assertEquals(10000f, responseDTO.getCustomerLimit(), 0.01f);
    }

    @Test
    void shouldApplyLoanApproved() {
        when(customerLoanResponseDTO.getResult()).thenReturn(LoanResult.APPROVED);
        when(loanSaveRequestDTO.getResult()).thenReturn(LoanResult.APPROVED);

        assertEquals(loanSaveRequestDTO.getResult(), customerLoanResponseDTO.getResult());
    }

    @Test
    void shouldNotApplyLoanRejected() {
        when(customerLoanResponseDTO.getResult()).thenReturn(LoanResult.REJECTED);
        when(loanSaveRequestDTO.getResult()).thenReturn(LoanResult.REJECTED);

        assertEquals(loanSaveRequestDTO.getResult(), customerLoanResponseDTO.getResult());
    }

}