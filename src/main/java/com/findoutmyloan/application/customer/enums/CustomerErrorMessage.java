package com.findoutmyloan.application.customer.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.errorMessage.BaseErrorMessage;

public enum CustomerErrorMessage implements BaseErrorMessage {
    CUSTOMER_NOT_FOUND("Customer Not Found!","Please check the id of the customer."),
    FIELD_CANNOT_BE_NULL("Field Cannot Be Null!","Please be sure that all fields are entered."),
    INFORMATION_MISSING("Information Mismatch!","Please make sure you have entered your information correctly."),
    IDENTITY_NO_MUST_BE_UNIQUE("Identity No Must Be Unique!","Please check the identity no of the customer."),
            ;
    private  final String message;
    private  final String detailMessage;

    CustomerErrorMessage(String message, String detailMessage){
        this.message = message;
        this.detailMessage = detailMessage;
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
