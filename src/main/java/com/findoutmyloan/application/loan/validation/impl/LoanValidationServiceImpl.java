package com.findoutmyloan.application.loan.validation.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerErrorMessage;
import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.general.errorMessage.GeneralErrorMessage;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import com.findoutmyloan.application.general.exception.IllegalFieldException;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.enums.LoanErrorMessage;
import com.findoutmyloan.application.loan.validation.LoanValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.findoutmyloan.application.loan.enums.LoanErrorMessage.AMOUNT_MUST_BE_POSITIVE;
import static com.findoutmyloan.application.loan.enums.LoanErrorMessage.FIELD_CANNOT_BE_NULL;

@Service
@RequiredArgsConstructor
public class LoanValidationServiceImpl implements LoanValidationService {
    private void validateIsAmountPositive(float amount) {
        if (amount<0) {
            throw new IllegalFieldException(AMOUNT_MUST_BE_POSITIVE);
        }
    }

    @Override
    public void validateLoan(Loan loan) {
        validateIsAmountPositive(loan.getAmount());
        validateAreFieldsNotNull(loan);
    }

    @Override
    public void validateCreditScore(int creditScore) {
       if (creditScore<0) {
           throw new GeneralBusinessException(GeneralErrorMessage.VALUE_CANNOT_BE_NEGATIVE);
       }
    }

    @Override
    public void validateCustomerProfile(CustomerProfiler customerProfiler) {
        if (customerProfiler==null) {
            throw new IllegalFieldException(CustomerErrorMessage.CUSTOMER_PROFILE_CANNOT_BE_NULL);
        }
    }


    private void validateAreFieldsNotNull(Loan loan) {
        boolean isNull=loan.getPaybackGuaranteeType()==null||String.valueOf(loan.getAmount()).isEmpty()||loan.getResult()==null;
        if (isNull) {
            throw new IllegalFieldException(FIELD_CANNOT_BE_NULL);
        }
    }
}
