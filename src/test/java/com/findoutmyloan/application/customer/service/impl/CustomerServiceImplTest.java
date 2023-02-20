package com.findoutmyloan.application.customer.service.impl;

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerTypeAccordingToMonthlyIncome;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.validation.CustomerValidationService;
import com.findoutmyloan.application.general.exception.ItemNotFoundException;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.security.service.AuthenticationService;
import com.findoutmyloan.application.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    Customer customer;
    @Mock
    AuthenticationService authenticationService;
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private LoanService loanService;

    @Mock
    private CustomerValidationService customerValidationService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CustomerRepository customerRepository;


    @Test
    void shouldGetLimitOfCustomer() {

    }

    @Test
    void shouldSaveCustomer() {
        long identityNo=81655500404L;
        when(passwordEncoder.encode(anyString())).thenReturn("123");
        CustomerSaveRequestDTO customerSaveRequestDTO=mock(CustomerSaveRequestDTO.class);

        when(customerSaveRequestDTO.getPassword()).thenReturn("123");
        when(customerSaveRequestDTO.getIdentityNo()).thenReturn(identityNo);

        Customer customer=mock(Customer.class);
        when(customer.getIdentityNo()).thenReturn(identityNo);
        when(customerSaveRequestDTO.getPassword()).thenReturn("123");

        when(customerRepository.save(any())).thenReturn(customer);
        CustomerResponseDTO result=customerService.saveCustomer(customerSaveRequestDTO);

        assertEquals(customer.getIdentityNo(), result.getIdentityNo());
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSaveCustomerParameterIsNull() {
        assertThrows(NullPointerException.class, ()->customerService.saveCustomer(null));
    }

    @Test
    void getCustomerTypeAccordingToMonthlyIncome() {
        long identityNo=81655500404L;
        Customer customer=mock(Customer.class);
        when(customer.getIdentityNo()).thenReturn(identityNo);
        when(customer.getPersonType()).thenReturn(PersonType.CUSTOMER);
        when(customerRepository.findByIdentityNo(identityNo)).thenReturn(Optional.of(customer));
        CustomerTypeAccordingToMonthlyIncome result=customerService.getCustomerTypeAccordingToMonthlyIncome(identityNo);

    }

    @Test
    void shouldFindCustomerByIdentityNoOrThrowExceptionFindCase() {
        long identityNo=81655500404L;
        Customer customer=mock(Customer.class);
        when(customer.getIdentityNo()).thenReturn(identityNo);
        when(customerRepository.findByIdentityNo(anyLong())).thenReturn(Optional.of(customer));
        Customer result=customerService.findCustomerByIdentityNoOrThrowException(identityNo);
        assertEquals(customer.getIdentityNo(), result.getIdentityNo());
    }

    @Test
    void shouldFindCustomerByIdentityNoOrThrowExceptionThrowCase() {
        assertThrows(ItemNotFoundException.class, ()->customerService.findCustomerByIdentityNoOrThrowException(anyLong()));
        verify(customerRepository, times(1)).findByIdentityNo(anyLong());
    }


    @Test
    void shouldGetByIdWithControlWithIdData() {
        long id=18L;
        long identityNo=81655500404L;
        Customer customer=mock(Customer.class);
        when(customer.getIdentityNo()).thenReturn(identityNo);
        when(customer.getPersonType()).thenReturn(PersonType.CUSTOMER);
        when(customer.getId()).thenReturn(id);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        CustomerDTO result=customerService.getByIdWithControlWithIdData(id);
        assertEquals(id, result.getId());
    }

    @Test
    void shouldNotGetByIdWithControlWithIdData() {
        assertThrows(ItemNotFoundException.class, ()->customerService.getByIdWithControlWithIdData(anyLong()));
        verify(customerRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldGetByIdWithControl() {
        long id=18L;
        long identityNo=81655500404L;
        Customer customer=mock(Customer.class);
        when(customer.getIdentityNo()).thenReturn(identityNo);
        when(customer.getPersonType()).thenReturn(PersonType.CUSTOMER);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        CustomerResponseDTO result=customerService.getByIdWithControl(id);
        assertEquals(identityNo, result.getIdentityNo());
    }

    @Test
    void shouldNotGetByIdWithControl() {
        assertThrows(ItemNotFoundException.class, ()->customerService.getByIdWithControl(anyLong()));
        verify(customerRepository, times(1)).findById(anyLong());
    }


    @Test
    void shouldDeleteAccountById() {
        Customer customer=mock(Customer.class);
        when(customer.getPersonType()).thenReturn(PersonType.CUSTOMER);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        customerService.deleteAccountByIdControl(customer.getId());
        verify(customerRepository, times(1)).findById(customer.getId());
    }

    @Test
    void shouldNotDeleteWhenAccountTypeIsNotCustomer() {
        Customer customer=mock(Customer.class);
        when(customer.getPersonType()).thenReturn(PersonType.SURETY);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        assertThrows(ItemNotFoundException.class, ()->customerService.deleteAccountByIdControl(customer.getId()));
    }

    @Test
    void shouldNotDeleteWhenParameterIsNull() {
        assertThrows(ItemNotFoundException.class, ()->customerService.deleteAccountByIdControl(null));
    }

    @Test
    void shouldNotDeleteAccountByIdControlWhenCustomerIsNotExists() {
        Customer customer=mock(Customer.class);
        assertThrows(ItemNotFoundException.class, ()->customerService.deleteAccountByIdControl(customer.getId()));
    }

    @Test
    void shouldUpdateCustomer() {
        Long identityNo=81655500404L;

        CustomerUpdateRequestDTO customerUpdateRequestDTO=mock(CustomerUpdateRequestDTO.class);
        Customer mockCustomer=mock(Customer.class);
        when(mockCustomer.getId()).thenReturn(18L);
        when(mockCustomer.getIdentityNo()).thenReturn(identityNo);
        when(mockCustomer.getPersonType()).thenReturn(PersonType.CUSTOMER);
        when(customerRepository.findByIdentityNo(anyLong())).thenReturn(Optional.of(mockCustomer));
        when(customerRepository.save(any())).thenReturn(mockCustomer);

        CustomerResponseDTO result=customerService.updateCustomer(customerUpdateRequestDTO);
        assertEquals(mockCustomer.getIdentityNo(), result.getIdentityNo());
    }

    @Test
    void shouldNotUpdateCustomerWhenCustomerDoesNotExists() {
        assertThrows(NullPointerException.class, ()->customerService.updateCustomer(null));
    }

    //TODO: should add shouldFindLoansByCustomerIdentityNoAndCustomerBirthDate
    @Test
    void shouldNotFindLoansByCustomerIdentityNoAndCustomerBirthDate() {
        long identityNo=7063284279L;
        LocalDate birthDate=LocalDate.of(1999, 1, 1);
        List<LoanDTO> result=customerService.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, DateUtil.convertToDate(birthDate));
        assertEquals(0, result.size());
    }
}