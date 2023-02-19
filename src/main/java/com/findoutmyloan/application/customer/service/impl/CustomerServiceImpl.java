package com.findoutmyloan.application.customer.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.enums.CustomerTypeAccordingToMonthlyIncome;
import com.findoutmyloan.application.customer.mapper.CustomerMapper;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.customer.validation.CustomerValidationService;
import com.findoutmyloan.application.general.exception.ItemNotFoundException;
import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.facade.dto.LoanApplicationRequestDTO;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.person.enums.PersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerValidationService customerValidationService;
    private final LoanService loanService;
    private final PasswordEncoder passwordEncoder;

    //fixed: @Lazy annotation is used to avoid circular dependency
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerValidationService customerValidationService, @Lazy LoanService loanService, PasswordEncoder passwordEncoder) {
        this.customerRepository=customerRepository;
        this.customerValidationService=customerValidationService;
        this.loanService=loanService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public float getLimitOfCustomer(LoanApplicationRequestDTO loanApplicationRequestDTO, float limitOfLoan) {
        Customer customer=findCustomerByIdentityNoOrThrowException(loanApplicationRequestDTO.getCustomerIdentityNo());
        float limitOfCustomer=customer.getCustomerLimit()+limitOfLoan;
        updateCustomerLimit(customer, limitOfCustomer);
        return limitOfCustomer;
    }

    private void updateCustomerLimit(Customer customer, float limitOfCustomer) {
        customer.setCustomerLimit(limitOfCustomer);
        customerRepository.save(customer);
    }

    @Override
    public CustomerResponseDTO saveCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer=CustomerMapper.INSTANCE.convertToCustomer(customerSaveRequestDTO);

        setAdditionalFields(customer);

        customerValidationService.validateCustomer(customer);
        String password=passwordEncoder.encode(customer.getPassword());
        customer.setPassword(password);

        Customer savedCustomer=customerRepository.save(customer);
        return CustomerMapper.INSTANCE.convertToCustomerResponseDTO(savedCustomer);
    }


    public CustomerTypeAccordingToMonthlyIncome getCustomerTypeAccordingToMonthlyIncome(float monthlyIncome) {
        if (isMonthlyIncomeInLowRange(monthlyIncome)) {
            return CustomerTypeAccordingToMonthlyIncome.LOW_INCOME;
        } else if (isMonthlyIncomeInMediumRange(monthlyIncome)) {
            return CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME;
        } else if (isMonthlyIncomeInHighRange(monthlyIncome)) {
            return CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME;
        }
        return null;
    }

    private boolean isMonthlyIncomeInLowRange(float monthlyIncome) {
        return monthlyIncome>=CustomerTypeAccordingToMonthlyIncome.LOW_INCOME.getMinimumMonthlyIncome()&&
                monthlyIncome<=CustomerTypeAccordingToMonthlyIncome.LOW_INCOME.getMaximumMonthlyIncome();
    }

    private boolean isMonthlyIncomeInMediumRange(float monthlyIncome) {
        return monthlyIncome>=CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME.getMinimumMonthlyIncome()&&
                monthlyIncome<=CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME.getMaximumMonthlyIncome();
    }

    private boolean isMonthlyIncomeInHighRange(float monthlyIncome) {
        return monthlyIncome>=CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME.getMinimumMonthlyIncome()&&monthlyIncome<=
                CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME.getMaximumMonthlyIncome();
    }

    private Customer findCustomerByIdOrThrowException(Long id) {
        Customer customer=(Customer) customerRepository.findById(id).orElseThrow(()->new ItemNotFoundException(CustomerErrorMessage.CUSTOMER_NOT_FOUND));
        if (customer.getPersonType()==PersonType.CUSTOMER)
            return customer;
        else
            throw new ItemNotFoundException(CustomerErrorMessage.CUSTOMER_NOT_FOUND);
    }

    public Customer findCustomerByIdentityNoOrThrowException(Long identityNo) {

        return customerRepository.findByIdentityNo(identityNo).orElseThrow(()->new ItemNotFoundException(CustomerErrorMessage.CUSTOMER_NOT_FOUND));

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public CustomerResponseDTO getByIdWithControl(Long id) {
        Customer customer=findCustomerByIdOrThrowException(id);
        return CustomerMapper.INSTANCE.convertToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerDTO getByIdWithControlWithIdData(Long id) {
        Customer customer=findCustomerByIdOrThrowException(id);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    public void deleteAccountByIdControl(Long id) {
        Customer customer=findCustomerByIdOrThrowException(id);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer customerToUpdate=findCustomerByIdentityNoOrThrowException(customerUpdateRequestDTO.getIdentityNo());
        setAdditionalFields(customerToUpdate);
        customerToUpdate.setName(customerUpdateRequestDTO.getName());
        customerToUpdate.setSurname(customerUpdateRequestDTO.getSurname());
        customerToUpdate.setMonthlyIncome(customerUpdateRequestDTO.getMonthlyIncome());
        customerToUpdate.setPhoneNumber(customerUpdateRequestDTO.getPhoneNumber());
        customerToUpdate.setBirthDate(customerUpdateRequestDTO.getBirthDate());
        customerRepository.save(customerToUpdate);

        return CustomerMapper.INSTANCE.convertToCustomerResponseDTO(customerToUpdate);
    }

    @Override
    public List<LoanDTO> findLoansByCustomerIdentityNoAndCustomerBirthDate(long identityNo, Date birthDate) {
        customerValidationService.validateCustomerByIdentityNoAndBirthDate(identityNo, birthDate);

        List<Loan> loans=loanService.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate);
        return LoanMapper.INSTANCE.convertToLoanDTOList(loans);
    }
}
