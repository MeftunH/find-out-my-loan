package com.findoutmyloan.application.customer.profiler.service.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.profiler.handler.CreditScoreHandler;
import com.findoutmyloan.application.customer.profiler.handler.impl.HighCreditScoreHandler;
import com.findoutmyloan.application.customer.profiler.handler.impl.LowCreditScoreHandler;
import com.findoutmyloan.application.customer.profiler.handler.impl.MediumCreditScoreHandler;
import com.findoutmyloan.application.customer.profiler.service.CustomerProfilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProfilerServiceImpl implements CustomerProfilerService {
    private final CreditScoreHandler creditScoreHandler;

    public CustomerProfilerServiceImpl() {
        creditScoreHandler=new HighCreditScoreHandler();
        creditScoreHandler.linkWith(new MediumCreditScoreHandler())
                .linkWith(new LowCreditScoreHandler());
    }

    public CustomerProfiler getCustomerProfile(int creditScore, float monthlyIncome) {
        return creditScoreHandler.handle(creditScore, monthlyIncome);
    }
}
