package com.findoutmycreditscore.application.generic.errorMessage;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public enum GenericErrorMessage implements BaseErrorMessage {
    ITEM_NOT_FOUND("Item not found"),
    ITEM_ALREADY_EXISTS("Item already exists"),
    ;
    private String message;
    GenericErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message=message;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }
}
