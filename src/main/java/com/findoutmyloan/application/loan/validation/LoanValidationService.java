package com.findoutmyloan.application.loan.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.loan.entity.Loan;

public interface LoanValidationService {
    void validateLoan(Loan loan);
    void validateCreditScore(int creditScore);
    void validateCustomerProfile(CustomerProfiler customerProfiler);
}
