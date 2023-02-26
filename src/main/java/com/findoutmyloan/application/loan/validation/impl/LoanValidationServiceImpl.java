package com.findoutmyloan.application.loan.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.general.errorMessage.GeneralErrorMessage;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.validation.LoanValidationService;
import com.findoutmyloan.application.log.SingletonLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.findoutmyloan.application.loan.enums.LoanErrorMessage.AMOUNT_MUST_BE_POSITIVE;
import static com.findoutmyloan.application.loan.enums.LoanErrorMessage.FIELD_CANNOT_BE_NULL;

@Service
@RequiredArgsConstructor
public class LoanValidationServiceImpl implements LoanValidationService {
    private final SingletonLogger logger=SingletonLogger.getInstance();

    private void validateIsAmountPositive(float amount) {
        if (amount<0) {
            logger.warn("Loan amount "+amount+" is not positive");
            throw new IllegalFieldException(AMOUNT_MUST_BE_POSITIVE);
        }
    }

    @Override
    public void validateLoan(Loan loan) {
        validateAreFieldsNotNull(loan);
        validateIsAmountPositive(loan.getAmount());
    }

    @Override
    public void validateCreditScore(int creditScore) {
        if (creditScore<0) {
            logger.warn("Credit score "+creditScore+" is not positive");
            throw new GeneralBusinessException(GeneralErrorMessage.VALUE_CANNOT_BE_NEGATIVE);
        }
    }

    @Override
    public void validateCustomerProfile(CustomerProfiler customerProfiler) {
        if (customerProfiler==null) {
            logger.warn("Customer profile is null");
            throw new IllegalFieldException(CustomerErrorMessage.CUSTOMER_PROFILE_CANNOT_BE_NULL);
        }
    }


    private void validateAreFieldsNotNull(Loan loan) {
        boolean isNull=loan.getPaybackGuaranteeType()==null||String.valueOf(loan.getAmount()).isEmpty()||loan.getResult()==null;
        if (isNull) {
            logger.warn("Loan "+loan+" fields are null");
            throw new IllegalFieldException(FIELD_CANNOT_BE_NULL);
        }
    }
}
