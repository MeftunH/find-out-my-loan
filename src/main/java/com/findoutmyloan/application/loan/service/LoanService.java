package com.findoutmyloan.application.loan.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.entity.Loan;

import java.util.Date;
import java.util.List;

public interface LoanService {
     LoanDTO saveLoan(LoanSaveRequestDTO loanSaveRequestDTO);
     boolean isSuitableForCalculate(int creditScore);
     float calculateLimitOfLoan(int creditScore, float monthlyIncome);
     List<Loan> findLoansByCustomerIdentityNoAndCustomerBirthDate(long identityNo, Date birthDate);
}
