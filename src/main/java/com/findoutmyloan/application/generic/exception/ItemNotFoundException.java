package com.findoutmyloan.application.generic.exception;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.errorMessage.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends GenericBusinessException {
    public ItemNotFoundException(BaseErrorMessage message) {
        super(message);
    }
}