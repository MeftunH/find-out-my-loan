package com.findoutmyloan.application.loan.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum LoanErrorMessage implements BaseErrorMessage {

    AMOUNT_MUST_BE_POSITIVE("Amount must be positive", "Please check the amount,"),
    FIELD_CANNOT_BE_NULL("Loan Fields cannot be null", "Please check the fields");

    private final String message;
    private final String detailMessage;

    LoanErrorMessage(String message, String detailMessage) {
        this.message=message;
        this.detailMessage=detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }
}
