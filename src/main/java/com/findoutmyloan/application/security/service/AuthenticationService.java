package com.findoutmyloan.application.security.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.security.dto.SecurityLoginRequestDTO;

public interface AuthenticationService {
    CustomerResponseDTO register(CustomerSaveRequestDTO customerSaveRequestDTO);

    String login(SecurityLoginRequestDTO securityLoginRequestDTO);

    Customer getCurrentCustomer();
}
