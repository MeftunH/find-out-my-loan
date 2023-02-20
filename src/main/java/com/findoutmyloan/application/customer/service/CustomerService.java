package com.findoutmyloan.application.customer.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.loan.dto.LoanDTO;

import java.util.Date;
import java.util.List;

public interface CustomerService {
    CustomerResponseDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    CustomerResponseDTO getByIdWithControl(Long id);
    CustomerDTO getByIdWithControlWithIdData(Long id);
    Customer findCustomerByIdentityNoOrThrowException(Long identityNo);
    void deleteAccountByIdControl(Long id);

    CustomerResponseDTO updateAccount(CustomerUpdateRequestDTO customerUpdateRequestDTO);
    List<LoanDTO> findLoansByCustomerIdentityNoAndCustomerBirthDate(long identityNo, Date birthDate);
    float getUpdatedLimitOfCustomer(float limitOfLoan);
}
