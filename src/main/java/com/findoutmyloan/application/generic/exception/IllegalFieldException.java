package com.findoutmyloan.application.generic.exception;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.generic.errorMessage.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalFieldException extends GenericBusinessException{

    public IllegalFieldException(BaseErrorMessage message) {
        super(message);
    }
}
