package com.findoutmyloan.application.security.jwt;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.mapper.CustomerMapper;
import com.findoutmyloan.application.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private CustomerService customerService;

    public JwtUserDetailsService(@Lazy CustomerService customerService) {
        this.customerService=customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long identityNo=Long.valueOf(username);
        Customer customer=customerService.findCustomerByIdentityNoOrThrowException(identityNo);
        return JwtUserDetails.create(customer);
    }

    public UserDetails loadUserByUsername(Long id) {
        CustomerDTO customerDTO=customerService.getByIdWithControl(id);
        Customer customer=CustomerMapper.INSTANCE.convertToCustomer(customerDTO);
        return JwtUserDetails.create(customer);
    }
}
