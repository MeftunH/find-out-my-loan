package com.findoutmyloan.application.customer.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerResultDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerTypeAccordingToMonthlyIncome;
import com.findoutmyloan.application.loan.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

public interface CustomerService {
    CustomerResultDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    CustomerResultDTO getByIdWithControl(Long id);
    CustomerDTO getByIdWithControlWithIdData(Long id);
    Customer findCustomerByIdentityNoOrThrowException(Long identityNo);
    void deleteAccountByIdControl(Long id);
    CustomerTypeAccordingToMonthlyIncome getCustomerTypeAccordingToMonthlyIncome(float monthlyIncome);
    CustomerResultDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);
    List<LoanDTO> findLoansByCustomerIdentityNoAndCustomerBirthDate(long identityNo, Date birthDate) throws GeneralSecurityException;
    float getLimitOfCustomer(LoanApplicationRequestDTO loanApplicationRequestDTO, float limitOfLoan);
}
