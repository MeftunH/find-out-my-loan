package com.findoutmyloan.application.person.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum PersonErrorMessage implements BaseErrorMessage {
    IDENTITY_NO_MUST_BE_UNIQUE("Identity No Must Be Unique!","Please check the identity no of the customer."),
    PHONE_NUMBER_INVALID(  "Phone Number Invalid!","Please check the phone number." ),
    PHONE_NUMBER_MUST_BE_UNIQUE("Phone Number Must Be Unique!","Please check the phone number."),
    FIELD_CANNOT_BE_NULL("Field Cannot Be Null!","Please check the field."),
    IDENTITY_NO_INVALID("Identity No Invalid!","Please check the identity no.");

    private  final String message;
    private  final String detailMessage;
    PersonErrorMessage(String message, String detailMessage){
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
