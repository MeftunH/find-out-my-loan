package com.findoutmyloan.application.customer.service.impl;

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.validation.CustomerValidationService;
import com.findoutmyloan.application.general.exception.ItemNotFoundException;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.security.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @Mock
    CustomerValidationService customerValidationService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        authenticationService=mock(AuthenticationService.class);
    }

    @Test
    void shouldGetLimitOfCustomer() {
        customer=new Customer();
        customer.setCustomerLimit(1000.0f);
        customer.setIdentityNo(81655500404L);
        customer.setPersonType(PersonType.CUSTOMER);
        when(authenticationService.getCurrentCustomer()).thenReturn(customer);

        float limitOfLoan=500.0f;
        float expectedLimit=1500.0f;

        float actualLimit=customerService.getLimitOfCustomer(limitOfLoan);

        assertEquals(expectedLimit, actualLimit);
        verify(authenticationService, times(1)).getCurrentCustomer();
    }

    @Test
    void shouldSaveCustomer() {
        when(passwordEncoder.encode(anyString())).thenReturn("123");
        CustomerSaveRequestDTO customerSaveRequestDTO=mock(CustomerSaveRequestDTO.class);

        when(customerSaveRequestDTO.getPassword()).thenReturn("123");
        when(customerSaveRequestDTO.getIdentityNo()).thenReturn(81655500404L);

        Customer customer=mock(Customer.class);
        when(customer.getIdentityNo()).thenReturn(81655500404L);
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
    }

    @Test
    void shouldFindCustomerByIdentityNoOrThrowExceptionFindCase() {
        Customer customer=mock(Customer.class);
        when(customer.getIdentityNo()).thenReturn(81655500404L);
        when(customerRepository.findByIdentityNo(anyLong())).thenReturn(Optional.of(customer));
        Customer result=customerService.findCustomerByIdentityNoOrThrowException(81655500404L);
        assertEquals(customer.getIdentityNo(), result.getIdentityNo());
    }

    @Test
    void shouldFindCustomerByIdentityNoOrThrowExceptionThrowCase() {
        assertThrows(ItemNotFoundException.class, ()->customerService.findCustomerByIdentityNoOrThrowException(anyLong()));
        verify(customerRepository, times(1)).findByIdentityNo(anyLong());
    }


    @Test
    void shouldGetByIdWithControlWithIdData() {
        Customer customer=mock(Customer.class);
        when(customer.getIdentityNo()).thenReturn(81655500404L);
        when(customerRepository.findByIdentityNo(anyLong())).thenReturn(Optional.of(customer));
        CustomerResponseDTO result=customerService.getByIdWithControl(81655500404L);
        assertEquals(customer.getIdentityNo(), result.getIdentityNo());
    }

    @Test
    void shouldDeleteAccountById() {
        Customer customer=new Customer();
        customer.setPersonType(PersonType.CUSTOMER);
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        customerService.deleteAccountByIdControl(customer.getId());
    }

    @Test
    void shouldNotDeleteWhenAccountTypeIsNotCustomer() {
        Customer customer=new Customer();
        customer.setPersonType(PersonType.SURETY);

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
    void updateCustomer() {
    }

    @Test
    void findLoansByCustomerIdentityNoAndCustomerBirthDate() {
    }
}