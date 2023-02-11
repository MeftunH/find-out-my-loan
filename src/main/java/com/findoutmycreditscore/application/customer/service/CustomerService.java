package com.findoutmycreditscore.application.customer.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.customer.dto.CustomerDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerUpdateRequestDTO;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    CustomerDTO getByIdWithControl(Long id);

    void deleteCustomerByIdWithControl(Long id);

    CustomerDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);
}
