package com.findoutmyloan.application.customer.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public enum CustomerTypeAccordingToMonthlyIncome {
    LOW_INCOME(0f,5000F),
    MEDIUM_INCOME(5000f,10000f),
    HIGH_INCOME(10000f,Float.MAX_VALUE);
    private float minimumMonthlyIncome;
    private float maximumMonthlyIncome;

    CustomerTypeAccordingToMonthlyIncome(float minimumMonthlyIncome,float maximumMonthlyIncome) {
        this.minimumMonthlyIncome = minimumMonthlyIncome;
        this.maximumMonthlyIncome = maximumMonthlyIncome;
    }

    public float getMinimumMonthlyIncome() {
        return minimumMonthlyIncome;
    }

    public float getMaximumMonthlyIncome() {
        return maximumMonthlyIncome;
    }
}
