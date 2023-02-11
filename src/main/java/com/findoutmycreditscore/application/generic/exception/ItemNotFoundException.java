package com.findoutmycreditscore.application.generic.exception;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.generic.dto.RestResponse;
import com.findoutmycreditscore.application.generic.errorMessage.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends GenericBusinessException {
    public ItemNotFoundException(BaseErrorMessage message) {
        super(message);
    }
}
