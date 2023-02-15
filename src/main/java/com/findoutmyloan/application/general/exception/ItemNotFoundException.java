package com.findoutmyloan.application.general.exception;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends GeneralBusinessException {
    public ItemNotFoundException(BaseErrorMessage message) {
        super(message);
    }
}
