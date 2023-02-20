package com.findoutmyloan.application.customer.profiler.handler;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerProfiler;

public abstract class CreditScoreHandler {
    private CreditScoreHandler next;

    public CreditScoreHandler linkWith(CreditScoreHandler next) {
        this.next = next;
        return next;
    }

    public abstract CustomerProfiler handle(int creditScore, float monthlyIncome);

    protected CustomerProfiler next(int creditScore, float monthlyIncome) {
        if (next == null) {
            return null;
        }
        return next.handle(creditScore, monthlyIncome);
    }
}
