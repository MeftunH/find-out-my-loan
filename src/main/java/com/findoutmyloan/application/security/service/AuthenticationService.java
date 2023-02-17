package com.findoutmyloan.application.security.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.mapper.CustomerMapper;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.security.dto.SecurityLoginRequestDTO;
import com.findoutmyloan.application.security.enums.JwtConstant;
import com.findoutmyloan.application.security.jwt.JwtTokenGenerator;
import com.findoutmyloan.application.security.jwt.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
     CustomerResponseDTO register(CustomerSaveRequestDTO customerSaveRequestDTO);
     String login(SecurityLoginRequestDTO securityLoginRequestDTO);
     Customer getCurrentCustomer();
}
