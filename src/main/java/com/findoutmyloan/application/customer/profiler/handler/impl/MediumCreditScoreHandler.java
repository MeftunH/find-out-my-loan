package com.findoutmyloan.application.customer.profiler.handler.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.enums.CreditScoreType;
import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.enums.CustomerTypeAccordingToMonthlyIncome;
import com.findoutmyloan.application.customer.profiler.handler.CreditScoreHandler;

import java.util.HashMap;
import java.util.Map;

public class MediumCreditScoreHandler extends CreditScoreHandler {
    private final Map<CustomerTypeAccordingToMonthlyIncome, CustomerProfiler> profileMap;

    public MediumCreditScoreHandler() {
        profileMap = new HashMap<>();
        profileMap.put(CustomerTypeAccordingToMonthlyIncome.LOW_INCOME, CustomerProfiler.BRONZE);
        profileMap.put(CustomerTypeAccordingToMonthlyIncome.MEDIUM_INCOME, CustomerProfiler.SILVER);
        profileMap.put(CustomerTypeAccordingToMonthlyIncome.HIGH_INCOME, CustomerProfiler.GOLD);
    }

    @Override
    public CustomerProfiler handle(int creditScore, float monthlyIncome) {
        if (creditScore >= CreditScoreType.MEDIUM_CREDIT_SCORE.getMinimumCreditScore()) {
            CustomerTypeAccordingToMonthlyIncome customerType = getCustomerTypeAccordingToMonthlyIncome(monthlyIncome);
            return profileMap.get(customerType);
        } else {
            return next(creditScore, monthlyIncome);
        }
    }

    private CustomerTypeAccordingToMonthlyIncome getCustomerTypeAccordingToMonthlyIncome(float monthlyIncome) {
        for (CustomerTypeAccordingToMonthlyIncome customerType : CustomerTypeAccordingToMonthlyIncome.values()) {
            if (monthlyIncome >= customerType.getMinimumMonthlyIncome() && monthlyIncome <= customerType.getMaximumMonthlyIncome()) {
                return customerType;
            }
        }
        return null;
    }
}
