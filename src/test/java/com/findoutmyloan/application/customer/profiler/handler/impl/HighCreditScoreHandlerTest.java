package com.findoutmyloan.application.customer.profiler.handler.impl;

import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HighCreditScoreHandlerTest {
    private HighCreditScoreHandler handler;

    @BeforeEach
    void setUp() {
        handler=new HighCreditScoreHandler();
    }

    @Test
    void shouldHandleWithHighCreditScore() {
        // Set up the test input
        int creditScore=1200;
        float monthlyIncome=12000;

        // Define the expected output
        CustomerProfiler expectedProfile=CustomerProfiler.PLATINUM;

        // Call the method under test
        CustomerProfiler actualProfile=handler.handle(creditScore, monthlyIncome);

        // Verify the result
        assertEquals(expectedProfile, actualProfile);
    }

    @Test
    void shouldHandleWithLowCreditScore() {
        // Set up the test input
        int creditScore=450;
        float monthlyIncome=8999;

        // Call the method under test
        CustomerProfiler actualProfile=handler.handle(creditScore, monthlyIncome);

        // Verify the result
        assertNull(actualProfile);
    }
}