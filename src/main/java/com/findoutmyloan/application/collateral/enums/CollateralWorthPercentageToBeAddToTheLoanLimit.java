package com.findoutmyloan.application.collateral.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public enum CollateralWorthPercentageToBeAddToTheLoanLimit {
    LOW_PERCENTAGE(0.1f),
    MEDIUM_PERCENTAGE(0.2f),
    HIGH_PERCENTAGE(0.25f),
    VERY_HIGH_PERCENTAGE(0.5f);

    private float worthPercentage;

    CollateralWorthPercentageToBeAddToTheLoanLimit(float worthPercentage) {
        this.worthPercentage = worthPercentage;
    }

    public float getWorthPercentage() {
        return worthPercentage;
    }
}
