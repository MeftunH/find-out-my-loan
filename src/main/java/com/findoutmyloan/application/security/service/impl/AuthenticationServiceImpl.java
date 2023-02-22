package com.findoutmyloan.application.security.service.impl;
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
import com.findoutmyloan.application.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;


    public AuthenticationServiceImpl(@Lazy CustomerService customerService, AuthenticationManager authenticationManager, JwtTokenGenerator jwtTokenGenerator) {
        this.customerService=customerService;
        this.authenticationManager=authenticationManager;
        this.jwtTokenGenerator=jwtTokenGenerator;
    }


    private static JwtUserDetails getCurrentJwtUserDetails(Authentication authentication) {
        JwtUserDetails jwtUserDetails=null;
        if (authentication!=null) {
            jwtUserDetails=(JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }

    public CustomerResponseDTO register(CustomerSaveRequestDTO customerSaveRequestDTO) {
        return customerService.saveCustomer(customerSaveRequestDTO);
    }

    public String login(SecurityLoginRequestDTO securityLoginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(securityLoginRequestDTO.getIdentityNo().toString(), securityLoginRequestDTO.getPassword());
        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenGenerator.generateJwtToken(authentication);
        return JwtConstant.BEARER.getConstant()+token;
    }



    public Customer getCurrentCustomer() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        CustomerDTO customerDTO=null;
        JwtUserDetails jwtUserDetails=jwtUserDetails=getCurrentJwtUserDetails(authentication);
        if (jwtUserDetails!=null) {
            customerDTO=customerService.getByIdWithControlWithIdData(jwtUserDetails.getId());
        }

        return CustomerMapper.INSTANCE.convertToCustomer(customerDTO);
    }
}
