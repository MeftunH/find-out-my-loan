package com.findoutmyloan.application.loan.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.entity.Customer;
import com.findoutmyloan.application.customer.repository.CustomerRepository;
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

    public boolean decisioner(int creditScore)
    {
        return creditScore>=500;
    }

  //Todo: Refactor this method
    @Override
    public LoanDTO saveLoan(LoanSaveRequestDTO loanSaveRequestDTO) {
        Loan loan=new Loan();
        if (decisioner(loanSaveRequestDTO.getCreditScore())) {
//            float customerMonthlyIncome=loanRepository.findMonthlyIncomeByCustomerId(loanSaveRequestDTO.getCustomerId());
            Customer customer=(Customer) customerRepository.findById(loanSaveRequestDTO.getCustomerId()).get();
            float customerMonthlyIncome=customer.getMonthlyIncome();
            if ((customer.getMonthlyIncome()>=0&&customer.getMonthlyIncome()<=5000)
                    &&loanSaveRequestDTO.getCreditScore()>=500&&loanSaveRequestDTO.getCreditScore()<1000) {

                loan.setCustomerId(loanSaveRequestDTO.getCustomerId());
                loan.setPaybackGuaranteeType(loanSaveRequestDTO.getPaybackGuaranteeType());
                loan.setResult(LoanResult.APPROVED);
                loan=loanRepository.save(loan);
                customer.setCustomerLimit(customer.getCustomerLimit()+10000);
                customerRepository.save(customer);

            }
        }
        return LoanMapper.INSTANCE.convertToLoanDto(loan);
    }
}
