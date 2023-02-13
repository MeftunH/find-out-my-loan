package com.findoutmyloan.application.loan.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.loan.dto.LoanDTO;
import com.findoutmyloan.application.loan.dto.LoanSaveRequestDTO;
import com.findoutmyloan.application.loan.entity.Loan;
import com.findoutmyloan.application.loan.mapper.LoanMapper;
import com.findoutmyloan.application.loan.repository.LoanRepository;
import com.findoutmyloan.application.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    public boolean decisioner(int creditScore)
    {
        return creditScore>=500;
    }

    @Override
    public LoanDTO saveCredit(LoanSaveRequestDTO loanSaveRequestDTO) {
        Loan loan= new Loan();
        loan.setCustomerId(loanSaveRequestDTO.getCustomerId());
        loan.setPaybackGuaranteeType(loanSaveRequestDTO.getPaybackGuaranteeType());
        loan.setConclusion(loanSaveRequestDTO.getConclusion());
        loan= loanRepository.save(loan);

        return LoanMapper.INSTANCE.convertToCreditDto(loan);
    }
}
