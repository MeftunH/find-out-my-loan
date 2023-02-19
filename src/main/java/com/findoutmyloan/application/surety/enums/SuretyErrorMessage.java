package com.findoutmyloan.application.surety.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum SuretyErrorMessage  implements BaseErrorMessage {
    SURETY_ERROR_MESSAGE("Surety error message", "Please check surety's fields"),
    PERSON_TYPE_MUST_BE_SURETY("Surety Person type must be surety", "Please check person type"),
    SURETY_TYPE_MUST_NOT_BE_NULL("Surety type must not be null", "Please check surety type"),
    SURETY_IDENTITY_NO_MUST_BE_UNIQUE("Surety Identity No Must Be Unique!","Please check the identity no of the surety."),
    SURETY_PHONE_NUMBER_INVALID(  "Surety Phone Number Invalid!","Please check the phone number." ),
    SURETY_PHONE_NUMBER_MUST_BE_UNIQUE("Surety Phone Number Must Be Unique!","Please check the phone number."),
    SURETY_IDENTITY_NO_INVALID("Surety Identity No Invalid!","Please check the identity no."),
    SURETY_BIRTH_DATE_INVALID("Surety Birth Date Invalid!","Please check the birth date."),
    SURETY_FIELD_CANNOT_BE_NULL("Field cannot be null", "Please check the fields" );
    private  final String message;
    private  final String detailMessage;
    SuretyErrorMessage(String message, String detailMessage){
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
