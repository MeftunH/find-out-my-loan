package com.findoutmyloan.application.util;

import com.findoutmyloan.application.general.exception.GeneralBusinessException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {
    private static SimpleDateFormat formatterDate;

    @BeforeAll
    public static void setUp() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    }

   @AfterAll
    public static void tearDown() {
        formatterDate=null;
    }
    @Test
    void shouldIgnoreTimeInformationWhenConvertToDateTime() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 16, 13, 30, 0);
        Date date=calendar.getTime();
        LocalDate result=DateUtil.convertToDateTime(date);
        assertEquals(LocalDate.of(2023, 2, 16), result);
    }

    @Test
    void shouldConvertDateToLocalDate() {
        Date date=new Date();
        LocalDate expected=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate result=DateUtil.convertToDateTime(date);
        assertEquals(expected, result);
    }

    @Test
    void shouldConvertToLocalDateTimeWhenTimeIsMidnight() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(2023, Calendar.FEBRUARY, 16, 23, 59, 59);
        Date date=calendar.getTime();
        LocalDateTime result=DateUtil.convertToDateTime(date).atTime(23, 59, 59);
        assertEquals(LocalDateTime.of(2023, 2, 16, 23, 59, 59), result);
    }

    @Test
    void shouldConvertDateInDifferentTimeZone() {
        Date date=new Date();
        ZoneId utcZoneId=ZoneId.of("UTC");
        LocalDate expected=date.toInstant().atZone(utcZoneId).toLocalDate();
        LocalDate result=DateUtil.convertToDateTime(Date.from(date.toInstant().atZone(utcZoneId).toInstant()));
        assertEquals(expected, result);
    }

    @Test
    void shouldConvertLocalDateToDate() {
        LocalDate localDate=LocalDate.now();
        Date expected=Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date result=DateUtil.convertToDate(localDate);
        assertEquals(expected, result);
    }

    @Test
    void shouldIgnoreTimeInformation() {
        LocalDate localDate=LocalDate.of(2023, 2, 16);
        Date result=DateUtil.convertToDate(localDate);
        assertEquals(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), result);
    }

    @Test
    void shouldConvertDateInDifferentTimeZoneConvertZone() {
        LocalDate localDate=LocalDate.now();
        ZoneId utcZoneId=ZoneId.of("UTC");
        Date expected=Date.from(localDate.atStartOfDay().atZone(utcZoneId).toInstant());
        Date result=DateUtil.convertToDate(localDate.atStartOfDay().atZone(utcZoneId).toLocalDate());
        assertNotEquals(expected, result);
    }

    @Test
    void shouldNotConvertToLocalDateWhenDateIsNull() {
        assertThrows(GeneralBusinessException.class, ()->DateUtil.convertToDateTime(null));
    }

    @Test
    void shouldNotConvertToDateWhenLocalDateIsNull() {
        assertThrows(GeneralBusinessException.class, ()->DateUtil.convertToDate(null));
    }

    @Test
    void shouldConvertLeapYear() {
        LocalDate localDate=LocalDate.of(2024, 2, 29);
        Date result=DateUtil.convertToDate(localDate);
        assertEquals(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), result);
    }
}