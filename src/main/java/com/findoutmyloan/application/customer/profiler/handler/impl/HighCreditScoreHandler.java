package com.findoutmyloan.application.customer.profiler.handler.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.creditscore.enums.CreditScoreType;
import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.profiler.handler.CreditScoreHandler;

public class HighCreditScoreHandler extends CreditScoreHandler {
    @Override
    public CustomerProfiler handle(int creditScore, float monthlyIncome) {
        if (creditScore >= CreditScoreType.HIGH_CREDIT_SCORE.getMinimumCreditScore()) {
            return CustomerProfiler.PLATINUM;
        } else {
            return next(creditScore, monthlyIncome);
        }
    }
}