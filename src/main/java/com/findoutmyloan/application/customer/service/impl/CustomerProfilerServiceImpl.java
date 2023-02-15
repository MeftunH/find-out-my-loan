package com.findoutmyloan.application.customer.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.enums.CreditScoreType;
import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.enums.CustomerTypeAccordingToMonthlyIncome;
import com.findoutmyloan.application.customer.service.CustomerProfilerService;
import com.findoutmyloan.application.customer.service.CustomerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CustomerProfilerServiceImpl implements CustomerProfilerService {
    private CustomerService customerService;

    @Autowired
    public CustomerProfilerServiceImpl(@NonNull @Lazy CustomerService customerService) {
        this.customerService=customerService;
    }

    @Override
    public CustomerProfiler getCustomerProfile(int creditScore, float monthlyIncome) {
        if (creditScore>=CreditScoreType.HIGH_CREDIT_SCORE.getMinimumCreditScore()) {
           return CustomerProfiler.PLATINUM;
        } else {
            if (CreditScoreType.getCreditScoreType(creditScore)==CreditScoreType.MEDIUM_CREDIT_SCORE) {
                if (customerService.getCustomerTypeAccordingToMonthlyIncome(monthlyIncome)==CustomerTypeAccordingToMonthlyIncome.LOW_INCOME) {
                  return CustomerProfiler.BRONZE;
                }
                else if (customerService.getCustomerTypeAccordingToMonthlyIncome(monthlyIncome)==CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME) {
                    return CustomerProfiler.SILVER;
                }
                else if (customerService.getCustomerTypeAccordingToMonthlyIncome(monthlyIncome)==CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME) {
                   return CustomerProfiler.GOLD;
                }
            }
        }
        return CustomerProfiler.WOOD;
    }
}
