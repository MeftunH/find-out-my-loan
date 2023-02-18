package com.findoutmyloan.application.surety.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum SuretyErrorMessage implements BaseErrorMessage {
    PERSON_TYPE_MUST_BE_SURETY("Person type must be surety", "Please check person type");
    private  final String message;
    private  final String detailMessage;

    SuretyErrorMessage(String message, String detailMessage) {
        this.message=message;
        this.detailMessage=detailMessage;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getDetailMessage() {
        return null;
    }
}
