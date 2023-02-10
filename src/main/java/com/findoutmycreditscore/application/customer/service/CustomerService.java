package com.findoutmycreditscore.application.customer.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.customer.dto.CustomerDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerSaveRequestDTO;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);
}
