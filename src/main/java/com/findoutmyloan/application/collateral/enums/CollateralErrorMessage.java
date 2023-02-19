package com.findoutmyloan.application.collateral.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum CollateralErrorMessage implements BaseErrorMessage {
    WORT_MUST_BE_POSITIVE("Worth must be positive", "Please enter a positive value for worth"),
    FIELD_CANNOT_BE_NULL("Field cannot be null", "Please check the fields");
   private final String message;
    private final String detailMessage;

    CollateralErrorMessage(String message, String detailMessage) {
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
