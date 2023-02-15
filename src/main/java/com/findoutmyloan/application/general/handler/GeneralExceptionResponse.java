package com.findoutmyloan.application.general.handler;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GeneralExceptionResponse {

    private Date errorDate;
    private String message;
    private String detail;
}
