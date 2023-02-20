package com.findoutmyloan.application.customer.profiler.handler.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.profiler.handler.CreditScoreHandler;

public class LowCreditScoreHandler extends CreditScoreHandler {
    @Override
    public CustomerProfiler handle(int creditScore, float monthlyIncome) {
        return CustomerProfiler.WOOD;
    }
}