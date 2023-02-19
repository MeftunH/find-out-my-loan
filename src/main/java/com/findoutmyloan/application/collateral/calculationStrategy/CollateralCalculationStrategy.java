package com.findoutmyloan.application.collateral.calculationStrategy;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public interface CollateralCalculationStrategy {
    float calculateLoanAmount(float collateralWorth,float amount);
}
