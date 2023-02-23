package com.findoutmyloan.application.surety.service.impl;

import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.security.service.AuthenticationService;
import com.findoutmyloan.application.surety.dto.SuretyDTO;
import com.findoutmyloan.application.surety.dto.SuretySaveRequestDTO;
import com.findoutmyloan.application.surety.entity.Surety;
import com.findoutmyloan.application.surety.repository.SuretyRepository;
import com.findoutmyloan.application.surety.validation.service.SuretyValidationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuretyServiceImplTest {
    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SuretyRepository suretyRepository;
    @Mock
    private SuretyValidationService suretyValidationService;
    @InjectMocks
    @Spy
    private SuretyServiceImpl suretyService;

    @BeforeAll
    static void setUp() {
        Authentication authentication=mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Customer customer=new Customer();
        customer.setId(1L);
        AuthenticationService authenticationService=mock(AuthenticationService.class);
    }

    @Test
    void shouldSaveSurety() {
        long customerId=1L;
        doReturn(customerId).when(suretyService).getCurrentCustomerId();
        long identityNo=81655500404L;
        SuretySaveRequestDTO suretySaveRequestDTO=mock(SuretySaveRequestDTO.class);
        when(suretySaveRequestDTO.getIdentityNo()).thenReturn(identityNo);

        Surety surety=mock(Surety.class);
        when(suretyRepository.save(any(Surety.class))).thenReturn(surety);

        SuretyDTO result=suretyService.saveSurety(suretySaveRequestDTO);

        verify(suretyRepository, times(1)).save(any());

        assertEquals(surety.getIdentityNo(), result.getIdentityNo());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSaveEntityParameterIsNull() {
        assertThrows(NullPointerException.class, ()->suretyService.saveSurety(null));
    }
}