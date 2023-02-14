package com.findoutmyloan.application.customer.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerTypeAccordingToMonthlyIncome;
import com.findoutmyloan.application.customer.mapper.CustomerMapper;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.generic.errorMessage.GenericErrorMessage;
import com.findoutmyloan.application.generic.exception.ItemNotFoundException;
import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.person.entity.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer=CustomerMapper.INSTANCE.convertToCustomer(customerSaveRequestDTO);
        setAdditionalFields(customer);
        Customer savedCustomer=customerRepository.save(customer);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(savedCustomer);
    }
    public CustomerTypeAccordingToMonthlyIncome getCustomerTypeAccordingToMonthlyIncome(float monthlyIncome) {
        if(isMonthlyIncomeInLowRange(monthlyIncome)){
            return CustomerTypeAccordingToMonthlyIncome.LOW_INCOME;
        }
        else if(isMonthlyIncomeInMediumRange(monthlyIncome)){
            return CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME;
        }
        else if(isMonthlyIncomeInHighRange(monthlyIncome)){
            return CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME;
        }
        return null;
    }
    private boolean isMonthlyIncomeInLowRange(float monthlyIncome){
        return monthlyIncome>=CustomerTypeAccordingToMonthlyIncome.LOW_INCOME.getMinimumMonthlyIncome()&&
               monthlyIncome<=CustomerTypeAccordingToMonthlyIncome.LOW_INCOME.getMaximumMonthlyIncome();
    }
    private boolean isMonthlyIncomeInMediumRange(float monthlyIncome){
        return monthlyIncome>=CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME.getMinimumMonthlyIncome()&&
               monthlyIncome<=CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME.getMaximumMonthlyIncome();
    }
    private boolean isMonthlyIncomeInHighRange(float monthlyIncome){
        return monthlyIncome>=CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME.getMinimumMonthlyIncome()&&monthlyIncome<=
               CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME.getMaximumMonthlyIncome();
    }

    //TODO: move to base service
    private Customer findCustomerByIdOrThrowException(Long id) {
        return (Customer) customerRepository.findById(id).orElseThrow(()->new ItemNotFoundException(GenericErrorMessage.ITEM_NOT_FOUND));
    }
    public Customer findCustomerByIdentityNoOrThrowException(Long id) {
        return (Customer) customerRepository.findByIdentityNo(id).orElseThrow(()->new ItemNotFoundException(GenericErrorMessage.ITEM_NOT_FOUND));
    }

    //TODO: move to base service
    @Override
    public CustomerDTO getByIdWithControl(Long id) {
        Customer customer=findCustomerByIdOrThrowException(id);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    public void deleteCustomerByIdWithControl(Long id) {
        Customer customer=findCustomerByIdOrThrowException(id);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(customerUpdateRequestDTO);
        Customer customerToUpdate = findCustomerByIdentityNoOrThrowException(customer.getIdentityNo());
        setAdditionalFields(customerToUpdate);
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setSurname(customer.getSurname());
        customerToUpdate.setCustomerLimit(customer.getCustomerLimit());
        customerToUpdate.setMonthlyIncome(customer.getMonthlyIncome());
        customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerToUpdate.setBirthDate(customer.getBirthDate());
//        customerToUpdate.setBaseAdditionalFields(customer.getBaseAdditionalFields());
        customerRepository.save(customerToUpdate);

        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }
}
