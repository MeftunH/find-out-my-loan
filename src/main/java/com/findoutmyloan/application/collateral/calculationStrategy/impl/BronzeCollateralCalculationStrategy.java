package com.findoutmyloan.application.collateral.calculationStrategy.impl;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmyloan.application.collateral.calculationStrategy.CollateralCalculationStrategy;

import static com.findoutmyloan.application.collateral.enums.CollateralWorthPercentageToBeAddToTheLoanLimit.*;

public class BronzeCollateralCalculationStrategy implements CollateralCalculationStrategy {
    public float calculateLoanAmount(float collateralWorth,float amount) {
        return amount+collateralWorth * LOW_PERCENTAGE.getWorthPercentage();
    }
}
