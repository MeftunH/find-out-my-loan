package com.findoutmyloan.application.customer.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum CustomerErrorMessage implements BaseErrorMessage {
    CUSTOMER_NOT_FOUND("Customer Not Found!","Please check the id of the customer."),
    FIELD_CANNOT_BE_NULL("Field Cannot Be Null!","Please be sure that all fields are entered."),
    INFORMATION_MISMATCH("Information Mismatch!","Please make sure you have entered your information correctly."),
    MONTHLY_INCOME_CANNOT_BE_NEGATIVE( "Monthly Income Cannot Be Negative!","Please check the monthly income." ),
    PASSWORD_MUST_BE_AT_LEAST_THREE_CHARACTERS( "Password Must Be At Least Three Characters!","Please check the password.");

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
