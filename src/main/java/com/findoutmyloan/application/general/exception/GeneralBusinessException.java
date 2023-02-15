package com.findoutmyloan.application.general.exception;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmyloan.application.general.errorMessage.BaseErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@RequiredArgsConstructor
@Getter
@Setter
public class GeneralBusinessException extends RuntimeException{
    private final BaseErrorMessage baseErrorMessage;

}
