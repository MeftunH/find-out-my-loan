package com.findoutmyloan.application.customer.profiler.handler.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.findoutmyloan.application.customer.enums.CustomerProfiler;
import org.junit.jupiter.api.Test;

class LowCreditScoreHandlerTest {
    @Test
    void testHandle() {
        // Mock the dependencies
        int creditScore = 400;
        float monthlyIncome = 1000;

        // Create the handler to be tested
        LowCreditScoreHandler handler = new LowCreditScoreHandler();

        // Define the test input and expected output
        CustomerProfiler expectedProfile = CustomerProfiler.WOOD;

        // Call the method under test
        CustomerProfiler actualProfile = handler.handle(creditScore, monthlyIncome);

        // Verify the result
        assertEquals(expectedProfile, actualProfile);
    }
}