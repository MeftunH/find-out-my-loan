package com.findoutmyloan.application.facade.service.impl;

import com.findoutmyloan.application.collateral.dto.CollateralSaveRequestDTO;
import com.findoutmyloan.application.collateral.enums.CollateralType;
import com.findoutmyloan.application.creditscore.dto.CreditScoreRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerCreditScoreRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.security.service.AuthenticationService;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.enums.SuretyType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuilderFacadeImplTest {
    @Mock
    AuthenticationService authenticationService;
    @InjectMocks
    private BuilderFacadeImpl builderFacade;

    @Test
    void shouldInvokeBuilder() {
        LoanApplicationRequestDTO loanApplicationRequestDTO=mock(LoanApplicationRequestDTO.class);

        Customer customer=mock(Customer.class);

        Authentication authentication=mock(Authentication.class);
        SecurityContext securityContext=mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(authenticationService.getCurrentCustomer()).thenReturn(customer);

        when(loanApplicationRequestDTO.getCollateralType()).thenReturn(CollateralType.MONEY);
        when(loanApplicationRequestDTO.getSuretyName()).thenReturn("Maftun");
        when(loanApplicationRequestDTO.getSuretySurname()).thenReturn("Hashimli");
        when(loanApplicationRequestDTO.getSuretyIdentityNo()).thenReturn(81655500404L);
        when(loanApplicationRequestDTO.getSuretyPhoneNumber()).thenReturn("0555555555");
        when(loanApplicationRequestDTO.getSuretyType()).thenReturn(SuretyType.JOINT);

        BuilderFacadeImpl builderFacade=new BuilderFacadeImpl(authenticationService);


        builderFacade.invokeBuilder(loanApplicationRequestDTO);

        CreditScoreRequestDTO creditScoreRequestDTO = builderFacade.getCreditScoreRequestDTO();
        CollateralSaveRequestDTO collateralSaveRequestDTO = builderFacade.getCollateralSaveRequestDTO();
        SuretySaveRequestDTO suretySaveRequestDTO = builderFacade.getSuretySaveRequestDTO();
        CustomerCreditScoreRequestDTO customerCreditScoreRequestDTO = builderFacade.getCustomerCreditScoreRequestDTO();

        assertNotNull(creditScoreRequestDTO);
        assertNotNull(collateralSaveRequestDTO);
        assertNotNull(suretySaveRequestDTO);
        assertNotNull(customerCreditScoreRequestDTO);
        assertEquals(authenticationService.getCurrentCustomer().getIdentityNo(), creditScoreRequestDTO.getCustomerIdentityNo());
        assertNotNull(creditScoreRequestDTO.getCustomerCreditScoreRequestDTO());
        assertEquals(authenticationService.getCurrentCustomer().getName(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getName());
        assertEquals(authenticationService.getCurrentCustomer().getSurname(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getSurname());
        assertEquals(authenticationService.getCurrentCustomer().getBirthDate(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getBirthDate());
        assertEquals(authenticationService.getCurrentCustomer().getIdentityNo(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getIdentityNo());
        assertEquals(authenticationService.getCurrentCustomer().getPersonType(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getPersonType());
        assertEquals(authenticationService.getCurrentCustomer().getPhoneNumber(), creditScoreRequestDTO.getCustomerCreditScoreRequestDTO().getPhoneNumber());
        assertNotNull(creditScoreRequestDTO.getSuretySaveRequestDTO());
        assertEquals(loanApplicationRequestDTO.getSuretyName(), creditScoreRequestDTO.getSuretySaveRequestDTO().getName());
        assertEquals(loanApplicationRequestDTO.getSuretySurname(), creditScoreRequestDTO.getSuretySaveRequestDTO().getSurname());
        assertEquals(loanApplicationRequestDTO.getSuretyBirthDate(), creditScoreRequestDTO.getSuretySaveRequestDTO().getBirthDate());
        assertEquals(loanApplicationRequestDTO.getSuretyIdentityNo(), creditScoreRequestDTO.getSuretySaveRequestDTO().getIdentityNo());
        assertEquals(loanApplicationRequestDTO.getSuretyPersonType(), creditScoreRequestDTO.getSuretySaveRequestDTO().getPersonType());
        assertEquals(loanApplicationRequestDTO.getSuretyType(), creditScoreRequestDTO.getSuretySaveRequestDTO().getSuretyType());
        Mockito.verify(loanApplicationRequestDTO).getCollateralType();
    }

    @Test
    void shouldNotInvokeBuilderWhenAuthIsNull() {
        AuthenticationService authenticationService = mock(AuthenticationService.class);
        when(authenticationService.getCurrentCustomer()).thenReturn(null);

        LoanApplicationRequestDTO loanApplicationRequestDTO = mock(LoanApplicationRequestDTO.class);
        BuilderFacadeImpl builderFacade = new BuilderFacadeImpl(authenticationService);

        assertThrows(GeneralBusinessException.class, () -> builderFacade.invokeBuilder(loanApplicationRequestDTO));
    }

    @Test
    void shouldNotGetCustomerCreditScoreRequestDTOWhenBuilderNotInvoked() {
        CustomerCreditScoreRequestDTO response = builderFacade.getCustomerCreditScoreRequestDTO();
        assertNull(response);
    }

    @Test
    void shouldNotGetSuretySaveRequestDTOWhenBuilderNotInvoked() {
        SuretySaveRequestDTO response =  builderFacade.getSuretySaveRequestDTO();
        assertNull(response);
    }

    @Test
    void shouldNotGetCollateralSaveRequestDTOWhenBuilderNotInvoked() {
        CollateralSaveRequestDTO response = builderFacade.getCollateralSaveRequestDTO();
        assertNull(response);
    }

    @Test
    void shouldNotGetCreditScoreRequestDTOWhenBuilderNotInvoked() {
        CreditScoreRequestDTO response = builderFacade.getCreditScoreRequestDTO();
        assertNull(response);
    }
}