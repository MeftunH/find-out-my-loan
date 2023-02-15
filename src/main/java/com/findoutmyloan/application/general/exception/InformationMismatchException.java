package com.findoutmyloan.application.general.exception;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InformationMismatchException extends GeneralBusinessException {
    public InformationMismatchException(BaseErrorMessage message) {
        super(message);
    }
}
