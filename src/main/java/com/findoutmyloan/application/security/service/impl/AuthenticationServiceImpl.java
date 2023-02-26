package com.findoutmyloan.application.security.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.mapper.CustomerMapper;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.customer.validation.CustomerValidationService;
import com.findoutmyloan.application.general.exception.InformationMismatchException;
import com.findoutmyloan.application.person.validation.PersonValidationService;
import com.findoutmyloan.application.security.dto.SecurityLoginRequestDTO;
import com.findoutmyloan.application.security.enums.JwtConstant;
import com.findoutmyloan.application.security.jwt.JwtTokenGenerator;
import com.findoutmyloan.application.security.jwt.JwtUserDetails;
import com.findoutmyloan.application.security.service.AuthenticationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final PersonValidationService personValidationService;
    private final CustomerRepository customerRepository;

    public AuthenticationServiceImpl(@Lazy CustomerService customerService, AuthenticationManager authenticationManager, JwtTokenGenerator jwtTokenGenerator, PersonValidationService personValidationService, CustomerValidationService customerValidationService,
                                     CustomerRepository customerRepository) {
        this.customerService=customerService;
        this.authenticationManager=authenticationManager;
        this.jwtTokenGenerator=jwtTokenGenerator;
        this.personValidationService=personValidationService;
        this.customerRepository=customerRepository;
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
        personValidationService.validateTurkishIdentityNo(securityLoginRequestDTO.getIdentityNo(), CustomerErrorMessage.CUSTOMER_IDENTITY_NO_INVALID);
        checkCustomer(securityLoginRequestDTO);
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(securityLoginRequestDTO.getIdentityNo().toString(), securityLoginRequestDTO.getPassword());
        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenGenerator.generateJwtToken(authentication);
        return JwtConstant.BEARER.getConstant()+token;
    }
    private void checkCustomer(SecurityLoginRequestDTO securityLoginRequestDTO) {
        Customer customer=customerIdentityNoCheck(securityLoginRequestDTO);
        userPasswordCheck(securityLoginRequestDTO.getPassword(), customer);
    }
    private Customer customerIdentityNoCheck(SecurityLoginRequestDTO securityLoginRequestDTO) {
        return customerRepository.findByIdentityNo(securityLoginRequestDTO.getIdentityNo()).orElseThrow(()->new InformationMismatchException(CustomerErrorMessage.CUSTOMER_CREDENTIALS_INVALID));

    }

    private void userPasswordCheck(String password, Customer customer) {
        PasswordEncoder passEncoder=new BCryptPasswordEncoder();
        String encodedPassword=customer.getPassword();
        if (!passEncoder.matches(password, encodedPassword))
            throw new InformationMismatchException(CustomerErrorMessage.CUSTOMER_CREDENTIALS_INVALID);
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
