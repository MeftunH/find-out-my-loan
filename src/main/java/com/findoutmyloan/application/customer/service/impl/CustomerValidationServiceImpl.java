package com.findoutmyloan.application.customer.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.service.CustomerValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerValidationServiceImpl implements CustomerValidationService {
    private final CustomerRepository customerRepository;

    @Override
    public void validateCustomerByIdentityNoAndBirthDate(Long identityNo, Date birthDate) throws GeneralSecurityException {
        if(customerRepository.findByIdentityNoAndBirthDate(identityNo, birthDate).equals(Optional.empty()))
            throw new GeneralSecurityException(CustomerErrorMessage.INFORMATION_MISMATCH.getMessage());
    }
}