package com.findoutmyloan.application.customer.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    CustomerDTO getByIdWithControl(Long id);

    void deleteCustomerByIdWithControl(Long id);

    CustomerDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);
}