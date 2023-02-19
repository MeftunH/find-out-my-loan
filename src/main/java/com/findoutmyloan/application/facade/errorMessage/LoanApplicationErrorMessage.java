package com.findoutmyloan.application.facade.errorMessage;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum LoanApplicationErrorMessage implements BaseErrorMessage {
    APPLICATION_IS_NO_MATCH_WITH_PAYBACK_GUARANTEE_TYPE("Application is no match with payback guarantee type", "Please check payback guarantee type" );


    LoanApplicationErrorMessage(String message, String detailMessage) {
        this.message=message;
        this.detailMessage=detailMessage;
    }

    private final String message;
    private final String detailMessage;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getDetailMessage() {
        return detailMessage;
    }
}
