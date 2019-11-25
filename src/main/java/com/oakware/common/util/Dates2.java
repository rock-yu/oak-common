package com.oakware.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Dates2 {
    public static int logicalCalendarDaysBetween(LocalDate earlyDate, LocalDate lastDate) {
        return Math.abs((int) ChronoUnit.DAYS.between(earlyDate, lastDate));
    }

    public static LocalDate getDefaultToLocalDate() {
        return LocalDate.of(2200, 12, 31);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) return null;
        LocalDateTime instance = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        return instance;
    }

    public static Date toDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDate date) {
        return (date == null) ? null : new Date(getTime(date));
    }

    /**
     * Returns a string representation of the given date in ISO8601 standard
     */
    public static String toISO(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    public static String toDateString(LocalDate date, String pattern) {
        return (date == null) ? "" : DateTimeFormatter.ofPattern(pattern).format(date);
    }

    public static String toDateTimeString(LocalDateTime dateTime) {
        return (dateTime == null) ? null : dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String toDateTimeString(Date dateTime) {
        return (dateTime == null) ? null : toDateTimeString(toLocalDateTime(dateTime));
    }

    public static LocalDate toLocalDate(Date date) {
        return (date == null) ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate toLocalDate(String dateIsoString) {
        if(dateIsoString == null || dateIsoString.isEmpty()) {
            return null;
        }

        return LocalDate.parse(dateIsoString, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static YearMonth toYearMonth(Date date) {
        return (date == null) ? null : YearMonth.from(toLocalDate(date));
    }

    public static LocalDate yesterday() {
        return LocalDate.now().minusDays(1);
    }

    public static LocalDate tomorrow() {
        return LocalDate.now().plusDays(1);
    }

    // TODO: test case
    public static long getTime(LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Date to2359(LocalDate date) {
        return (date == null) ? null : Date.from(date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).minusSeconds(1).toInstant());
    }

    public static LocalDate firstDayOfMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static Date today2() {
        return toDate(LocalDate.now());
    }
}
