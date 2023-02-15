package com.findoutmyloan.application.customer.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerTypeAccordingToMonthlyIncome;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    CustomerDTO getByIdWithControl(Long id);
    Customer findCustomerByIdentityNoOrThrowException(Long id);
    void deleteCustomerByIdWithControl(Long id);
    CustomerTypeAccordingToMonthlyIncome getCustomerTypeAccordingToMonthlyIncome(float monthlyIncome);
    CustomerDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);
}
