package com.findoutmycreditscore.application.customer.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.customer.dto.CustomerDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmycreditscore.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmycreditscore.application.customer.entity.Customer;
import com.findoutmycreditscore.application.customer.mapper.CustomerMapper;
import com.findoutmycreditscore.application.customer.repository.CustomerRepository;
import com.findoutmycreditscore.application.customer.service.CustomerService;
import com.findoutmycreditscore.application.generic.entity.BaseAdditionalFields;
import com.findoutmycreditscore.application.generic.errorMessage.GenericErrorMessage;
import com.findoutmycreditscore.application.generic.exception.ItemNotFoundException;
import com.findoutmycreditscore.application.person.entity.Person;
import com.findoutmycreditscore.application.person.entity.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer=CustomerMapper.INSTANCE.convertToCustomer(customerSaveRequestDTO);
        setAdditionalFields(customer);

        Customer savedCustomer=customerRepository.save(customer);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(savedCustomer);
    }
    //TODO: move to base service
    private void setAdditionalFields(Customer customer) {
        BaseAdditionalFields baseAdditionalFields = customer.getBaseAdditionalFields();


        if (baseAdditionalFields == null){

            baseAdditionalFields = new BaseAdditionalFields();
            customer.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (customer.getId() == null){

            baseAdditionalFields.setCreatedDate(new Date());
        }

        baseAdditionalFields.setUpdatedDate(new Date());
    }

    //TODO: move to base service
    private Customer findCustomerByIdOrThrowException(Long id) {
        return (Customer) customerRepository.findById(id).orElseThrow(()->new ItemNotFoundException(GenericErrorMessage.ITEM_NOT_FOUND));
    }

    private Customer findCustomerByIdentityNoOrThrowException(Long id) {
        return (Customer) customerRepository.findByIdentityNo(id).orElseThrow(()->new ItemNotFoundException(GenericErrorMessage.ITEM_NOT_FOUND));
    }

    //TODO: move to base service
    @Override
    public CustomerDTO getByIdWithControl(Long id) {
        Customer customer=findCustomerByIdOrThrowException(id);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    //TODO: move to base service
    public void deleteCustomerByIdWithControl(Long id) {
        Customer customer=findCustomerByIdOrThrowException(id);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(customerUpdateRequestDTO);
        setAdditionalFields(customer);
        Customer customerToUpdate = findCustomerByIdentityNoOrThrowException(customer.getIdentityNo());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setSurname(customer.getSurname());
        customerToUpdate.setCustomerLimit(customer.getCustomerLimit());
        customerToUpdate.setMonthlyIncome(customer.getMonthlyIncome());
        customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerToUpdate.setBirthDate(customer.getBirthDate());
        customerToUpdate.setPaybackGuaranteeType(customer.getPaybackGuaranteeType());
        customerToUpdate.setBaseAdditionalFields(customer.getBaseAdditionalFields());
        customerRepository.save(customerToUpdate);

        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }
}
