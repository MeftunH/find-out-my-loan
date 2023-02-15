package com.findoutmyloan.application.loan.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.enums.CreditScoreType;
import com.findoutmyloan.application.customer.mapper.CustomerMapper;
import com.findoutmyloan.application.customer.service.CustomerProfilerService;
import com.findoutmyloan.application.customer.service.CustomerService;
import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.repository.LoanRepository;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.notification.event.CustomerLoanApplicationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class LoanServiceImpl extends BaseService<Loan> implements LoanService {
    private final LoanRepository loanRepository;
    private final CustomerProfilerService customerProfilerService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CustomerService customerService;

    @Override
    public boolean isSuitableForCalculate(int creditScore) {
        return creditScore>=CreditScoreType.LOW_CREDIT_SCORE.getMaximumCreditScore();
    }

    @Override
    public float calculateLimitOfCustomer(int creditScore, float monthlyIncome) {
        float limit=0;
        final float bronzeCustomerLimit=10000.0f;
        final float silverCustomerLimit=20000.0f;
        final float loanLimitMultiplier=4.0f;
        final float halfDividerValue=2.0f;
        switch (customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)) {
            case BRONZE -> limit=bronzeCustomerLimit;
            case SILVER -> limit=silverCustomerLimit;
            case GOLD -> limit=limit+loanLimitMultiplier*monthlyIncome/halfDividerValue;
            case PLATINUM -> limit=limit+loanLimitMultiplier*monthlyIncome;
        }
        return limit;
    }
    @Override
    public LoanDTO saveLoan(LoanSaveRequestDTO loanSaveRequestDTO) {
        Loan loan=LoanMapper.INSTANCE.convertToLoan(loanSaveRequestDTO);
        setAdditionalFields(loan);
        loanRepository.save(loan);
        applicationEventPublisher.publishEvent(new CustomerLoanApplicationEvent(this,
                CustomerMapper.INSTANCE.convertToCustomer(customerService.getByIdWithControl(loanSaveRequestDTO.getCustomerId()))));

        return LoanMapper.INSTANCE.convertToLoanDto(loan);
    }
    @Override
    public List<Loan> findLoansByCustomerIdentityNoAndCustomerBirthDate(long identityNo, Date birthDate) {
        return loanRepository.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate);
    }

}
