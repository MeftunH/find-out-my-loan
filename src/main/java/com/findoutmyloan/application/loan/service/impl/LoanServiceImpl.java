package com.findoutmyloan.application.loan.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.enums.CreditScoreType;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.profiler.service.CustomerProfilerService;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.generic.service.BaseService;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.repository.LoanRepository;
import com.findoutmyloan.application.loan.service.LoanService;
import com.findoutmyloan.application.loan.validation.LoanValidationService;
import com.findoutmyloan.application.log.SingletonLogger;
import com.findoutmyloan.application.notification.enums.NotificationType;
import com.findoutmyloan.application.notification.service.NotificationService;
import com.findoutmyloan.application.notification.sms.enums.SmsMessageTemplate;
import lombok.RequiredArgsConstructor;
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
    private final NotificationService notificationService;
    private final CustomerRepository customerRepository;
    private final LoanValidationService loanValidationService;
    private final SingletonLogger logger=SingletonLogger.getInstance();

    @Override
    public boolean isSuitableForCalculate(int creditScore) {
        logger.info("Checking if credit score"+creditScore+"is suitable for calculate");
        return creditScore>=CreditScoreType.LOW_CREDIT_SCORE.getMaximumCreditScore();
    }

    @Override
    public float calculateLimitOfLoan(int creditScore, float monthlyIncome) {
        loanValidationService.validateCreditScore(creditScore);
        loanValidationService.validateCustomerProfile(customerProfilerService.getCustomerProfile(creditScore, monthlyIncome));
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
        long currentCustomerId=getCurrentCustomerId();
        Customer customer=(Customer) customerRepository.getReferenceById(currentCustomerId);
        setAdditionalFields(loan);

        loanValidationService.validateLoan(loan);

        loan.setCustomerId(getCurrentCustomerId());
        loanRepository.save(loan);
        notificationService.notify(NotificationType.SMS,
                SmsMessageTemplate.LOAN_APPLICATION_SUBMITTED.getMessage(),
                customer);
        logger.info("Loan with id "+loan.getId()+" is saved");
        return LoanMapper.INSTANCE.convertToLoanDto(loan);
    }

    @Override
    public List<Loan> findLoansByCustomerIdentityNoAndCustomerBirthDate(long identityNo, Date birthDate) {
        return loanRepository.findLoansByCustomerIdentityNoAndCustomerBirthDate(identityNo, birthDate);
    }

}
