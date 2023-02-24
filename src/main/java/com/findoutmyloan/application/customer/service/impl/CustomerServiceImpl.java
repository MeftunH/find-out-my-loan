package com.findoutmyloan.application.customer.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.service.impl.CollateralServiceImpl;
import com.findoutmyloan.application.customer.dto.CustomerDTO;
import com.findoutmyloan.application.customer.dto.CustomerResponseDTO;
import com.findoutmyloan.application.customer.dto.CustomerSaveRequestDTO;
import com.findoutmyloan.application.customer.dto.CustomerUpdateRequestDTO;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.mapper.CustomerMapper;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.customer.validation.CustomerValidationService;
import com.findoutmyloan.application.general.exception.ItemNotFoundException;
import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.person.enums.PersonType;
import com.findoutmyloan.application.security.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


    //fixed: @Lazy annotation is used to avoid circular dependency
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerValidationService customerValidationService, @Lazy LoanService loanService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
        this.customerRepository=customerRepository;
        this.customerValidationService=customerValidationService;
        this.loanService=loanService;
        this.passwordEncoder=passwordEncoder;
        this.authenticationService=authenticationService;
    }

    public float getUpdatedLimitOfCustomer(float limitOfLoan) {
        Customer customer=findCustomerByIdentityNoOrThrowException(authenticationService.getCurrentCustomer().getIdentityNo());
        float limitOfCustomer=customer.getCustomerLimit()+limitOfLoan;
        updateCustomerLimit(customer, limitOfCustomer);
        logger.info("Customer limit is updated to:{} ", limitOfCustomer);
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
        logger.info("Customer {} saved ", savedCustomer);
        return CustomerMapper.INSTANCE.convertToCustomerResponseDTO(savedCustomer);
    }

    private Customer findCustomerByIdOrThrowException(Long id) {
        Customer customer=(Customer) customerRepository.findById(id).orElseThrow(()->new ItemNotFoundException(CustomerErrorMessage.CUSTOMER_NOT_FOUND));
        if (customer.getPersonType()==PersonType.CUSTOMER)
            return customer;
        else {
            logger.warn("Customer does not exists by id: {} ", id);
            throw new ItemNotFoundException(CustomerErrorMessage.CUSTOMER_NOT_FOUND);
        }
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
        logger.info("Customer deleted by id: {} ", id);
    }

    @Override
    public CustomerResponseDTO updateAccount(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer customerToUpdate=findCustomerByIdentityNoOrThrowException(customerUpdateRequestDTO.getIdentityNo());
        setAdditionalFields(customerToUpdate);
        customerToUpdate.setName(customerUpdateRequestDTO.getName());
        customerToUpdate.setSurname(customerUpdateRequestDTO.getSurname());
        customerToUpdate.setMonthlyIncome(customerUpdateRequestDTO.getMonthlyIncome());
        customerToUpdate.setPhoneNumber(customerUpdateRequestDTO.getPhoneNumber());
        customerToUpdate.setBirthDate(customerUpdateRequestDTO.getBirthDate());

        customerValidationService.validateCustomer(customerToUpdate);

        customerRepository.save(customerToUpdate);
        logger.info("Customer updated {} to: {} ", customerUpdateRequestDTO, customerToUpdate);

        return CustomerMapper.INSTANCE.convertToCustomerResponseDTO(customerToUpdate);
    }

    @Override
    public List<LoanDTO> findLoansByCustomerIdentityNoAndCustomerBirthDate(long identityNo, Date birthDate) {
        customerValidationService.validateCustomerByIdentityNoAndBirthDate(identityNo, birthDate);

        List<Loan> loans=loanService.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate);
        return LoanMapper.INSTANCE.convertToLoanDTOList(loans);
    }
}
