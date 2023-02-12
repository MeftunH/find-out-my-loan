package com.findoutmycreditscore.application.util;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    public static LocalDate convertToDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertToDate(LocalDate dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
    }
}
