package com.findoutmyloan.application.loan.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public enum LoanApplication {
    HAS_BEEN_RECEIVED("Your loan application has been received")
    ;
    private String message;

    LoanApplication(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
