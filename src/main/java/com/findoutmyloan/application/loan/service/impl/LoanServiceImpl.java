package com.findoutmyloan.application.loan.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.collateral.enums.CollateralWorthPercentageToBeAddToTheLoanLimit;
import com.findoutmyloan.application.creditscore.enums.CreditScoreType;
import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
import com.findoutmyloan.application.customer.service.CustomerProfilerService;
import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.enums.LoanResult;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.repository.LoanRepository;
import com.findoutmyloan.application.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final CustomerProfilerService customerProfilerService;

   @Override
    public boolean isSuitableForCalculate(int creditScore)
    {
        return creditScore>=CreditScoreType.LOW_CREDIT_SCORE.getMaximumCreditScore();
    }

    @Override
    public float calculateLimitOfCustomer(int creditScore, float monthlyIncome)
    {
        float limit=0;
        final float bronzeCustomerLimit=10000.0f;
        final float silverCustomerLimit=20000.0f;
        final float loanLimitMultiplier=4.0f;
        final float halfDividerValue=2.0f;
        switch (customerProfilerService.getCustomerProfile(creditScore, monthlyIncome)) {
            case BRONZE ->
                    limit=bronzeCustomerLimit;
            case SILVER ->
                    limit=silverCustomerLimit;
            case GOLD ->
                    limit=limit+loanLimitMultiplier*monthlyIncome/halfDividerValue;
            case PLATINUM ->
                    limit=limit+loanLimitMultiplier*monthlyIncome;
        }
        return limit;
    }

  //Todo: Refactor this method
    @Override
    public LoanDTO saveLoan(LoanSaveRequestDTO loanSaveRequestDTO) {
        Loan loan = loanRepository.save(LoanMapper.INSTANCE.convertToLoan(loanSaveRequestDTO));
        return LoanMapper.INSTANCE.convertToLoanDto(loan);
    }
}
