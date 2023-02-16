package com.findoutmyloan.application.util;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import com.findoutmyloan.application.general.errorMessage.GeneralErrorMessage;
import com.findoutmyloan.application.general.exception.GeneralBusinessException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    public static LocalDate convertToDateTime(Date dateToConvert) {
        if(dateToConvert == null)
            throw new GeneralBusinessException(GeneralErrorMessage.DATE_COULD_NOT_BE_CONVERTED);
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public static Date convertToDate(LocalDate dateToConvert) {
        if(dateToConvert == null)
            throw new GeneralBusinessException(GeneralErrorMessage.DATE_COULD_NOT_BE_CONVERTED);
        return Date
                .from(dateToConvert.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
    }

}
