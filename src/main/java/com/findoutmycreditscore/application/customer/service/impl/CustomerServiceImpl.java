package com.findoutmycreditscore.application.customer.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.customer.dto.CustomerDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmycreditscore.application.customer.entity.Customer;
import com.findoutmycreditscore.application.customer.mapper.CustomerMapper;
import com.findoutmycreditscore.application.customer.repository.CustomerRepository;
import com.findoutmycreditscore.application.customer.service.CustomerService;
import com.findoutmycreditscore.application.generic.errorMessage.GenericErrorMessage;
import com.findoutmycreditscore.application.generic.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(customerSaveRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(savedCustomer);
    }

   //TODO: move to base service
    @Override
    public CustomerDTO getByIdWithControl(Long id) {
        Customer customer =(Customer) customerRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(GenericErrorMessage.ITEM_NOT_FOUND));
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }
}
