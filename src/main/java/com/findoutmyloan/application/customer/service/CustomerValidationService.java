package com.findoutmyloan.application.customer.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Optional;

public interface CustomerValidationService{
    void validateCustomerByIdentityNoAndBirthDate(Long identityNo, Date birthDate) throws GeneralSecurityException;
}
