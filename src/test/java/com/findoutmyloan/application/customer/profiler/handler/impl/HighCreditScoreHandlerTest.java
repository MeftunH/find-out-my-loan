package com.findoutmyloan.application.customer.profiler.handler.impl;

import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighCreditScoreHandlerTest {

    @Test
    void shouldHandleWithHighCreditScore() {
        // Set up the test input
        int creditScore = 1200;
        float monthlyIncome = 12000;

        // Create the handler to be tested
        HighCreditScoreHandler handler = new HighCreditScoreHandler();

        // Define the expected output
        CustomerProfiler expectedProfile = CustomerProfiler.PLATINUM;

        // Call the method under test
        CustomerProfiler actualProfile = handler.handle(creditScore, monthlyIncome);

        // Verify the result
        assertEquals(expectedProfile, actualProfile);
    }

    @Test
    void shouldHandleWithLowCreditScore() {
        // Set up the test input
        int creditScore = 450;
        float monthlyIncome = 8999;

        // Create the handler to be tested
        HighCreditScoreHandler handler = new HighCreditScoreHandler();

        // Call the method under test
        CustomerProfiler actualProfile = handler.handle(creditScore, monthlyIncome);

        // Verify the result
        assertEquals(null, actualProfile);
    }
}