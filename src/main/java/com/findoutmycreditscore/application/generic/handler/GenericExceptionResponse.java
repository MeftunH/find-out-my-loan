package com.findoutmycreditscore.application.generic.handler;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GenericExceptionResponse {

    private Date errorDate;
    private String message;
    private String detail;
}
