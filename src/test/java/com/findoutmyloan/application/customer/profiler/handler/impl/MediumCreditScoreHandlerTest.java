package com.findoutmyloan.application.customer.profiler.handler.impl;

import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import com.findoutmyloan.application.customer.profiler.handler.CreditScoreHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MediumCreditScoreHandlerTest {
    private CreditScoreHandler nextHandler;
    private MediumCreditScoreHandler handler;

    @BeforeEach
    void setUp() {
        nextHandler=mock(CreditScoreHandler.class);
        handler=new MediumCreditScoreHandler();
        handler.linkWith(nextHandler);
    }

    @Test
    void testHandleWithMediumCreditScoreAndLowMonthlyIncome() {
        // Mock the dependencies
        CustomerProfiler nextProfile=CustomerProfiler.PLATINUM;
        when(nextHandler.handle(700, 1000)).thenReturn(nextProfile);

        // Define the test inputs and expected output
        int creditScore=750;
        float monthlyIncome=500;
        CustomerProfiler expectedProfile=CustomerProfiler.BRONZE;

        // Call the method under test
        CustomerProfiler actualProfile=handler.handle(creditScore, monthlyIncome);

        // Verify the result
        assertEquals(expectedProfile, actualProfile);
    }

    @Test
    void testHandleWithLowCreditScoreAndLowMonthlyIncome() {
        // Mock the dependencies
        CreditScoreHandler nextHandler=mock(CreditScoreHandler.class);
        CustomerProfiler nextProfile=CustomerProfiler.BRONZE;
        when(nextHandler.handle(600, 1000)).thenReturn(nextProfile);

        // Define the test inputs and expected output
        int creditScore=550;
        float monthlyIncome=500;
        CustomerProfiler expectedProfile=nextProfile;

        // Call the method under test
        CustomerProfiler actualProfile=handler.handle(creditScore, monthlyIncome);

        // Verify the result
        assertEquals(expectedProfile, actualProfile);
    }
}