package com.findoutmyloan.application.loan.validation;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.entity.Loan;

public interface LoanValidationService {
    void validateLoan(Loan loan);

}
