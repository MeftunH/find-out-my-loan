package com.findoutmyloan.application.customer.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;

public enum CustomerErrorMessage implements BaseErrorMessage {
    CUSTOMER_NOT_FOUND("Customer Not Found!","Please check the id of the customer."),
    FIELD_CANNOT_BE_NULL("Customer Fields Cannot Be Null!","Please be sure that all fields are entered."),
    INFORMATION_MISMATCH("Customer Information Mismatch!","Please make sure you have entered your information correctly."),
    MONTHLY_INCOME_CANNOT_BE_NEGATIVE( "Monthly Income Cannot Be Negative!","Please check the monthly income." ),
    PASSWORD_MUST_BE_AT_LEAST_THREE_CHARACTERS( "Password Must Be At Least Three Characters!","Please check the password."),
    PERSON_TYPE_MUST_BE_CUSTOMER("Person Type Must Be Customer!","Please check the person type."),
    CUSTOMER_IDENTITY_NO_MUST_BE_UNIQUE("Customer Identity No Must Be Unique!","Please check the identity no of the customer."),
    CUSTOMER_PHONE_NUMBER_INVALID(  "Customer Phone Number Invalid!","Please check the phone number." ),
    CUSTOMER_PHONE_NUMBER_MUST_BE_UNIQUE("Customer Phone Number Must Be Unique!","Please check the phone number."),
    CUSTOMER_IDENTITY_NO_INVALID("Customer Identity No Invalid!","Please check the identity no."),
    CUSTOMER_BIRTH_DATE_INVALID("Customer Birth Date Invalid!","Please check the birth date."),
    CUSTOMER_PROFILE_NOT_FOUND("Customer Profile Not Found!","Please check the profile of the customer."),
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
