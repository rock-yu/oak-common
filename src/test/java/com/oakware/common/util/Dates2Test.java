package com.oakware.common.util;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Dates2Test {
    private LocalDate localDate = LocalDate.of(2016, 12, 15);

    @Before
    public void setUp() throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("Australia/Sydney"));
    }

    @Test
    public void testToDateString() {
        LocalDate date = LocalDate.of(2009, 8, 30);
        assertEquals("30_08_2009", Dates2.toDateString(date, "dd_MM_yyyy"));
        assertEquals("30-Aug", Dates2.toDateString(date, "dd-MMM"));
        assertEquals("30-08-2009", Dates2.toDateString(date, "dd-MM-yyyy"));
        assertEquals("", Dates2.toDateString(null, "dd-MM-yyyy"));
    }

    @Test
    public void deltaCalendarDays() {
        assertThat(Dates2.logicalCalendarDaysBetween(localDate, localDate), is(0));
        assertThat(Dates2.logicalCalendarDaysBetween(localDate, localDate.plusDays(1)), is(1));
        assertThat(Dates2.logicalCalendarDaysBetween(localDate, localDate.plusDays(40)), is(40));
    }

    @Test
    public void toLocalDate() throws Exception {
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        assertEquals(this.localDate, Dates2.toLocalDate(date));
        assertEquals(null, Dates2.toLocalDate((String)null));
    }

    @Test
    public void yesterday() throws Exception {
        LocalDate yesterday = Dates2.yesterday();
        LocalDate today = LocalDate.now();
        assertEquals(today, yesterday.plusDays(1));
    }

    @Test
    public void toZonedDateTime() throws Exception {
        Date date = Dates2.toDate(LocalDate.of(2016, 12, 15));
        LocalDateTime localDateTime = Dates2.toLocalDateTime(date);
        assertEquals(localDateTime.getYear(), 2016);
        assertEquals(localDateTime.getMonthValue(), 12);
        assertEquals(localDateTime.getDayOfMonth(), 15);
        assertEquals(localDateTime.getHour(), 0);
        assertEquals(localDateTime.getMinute(), 0);
        assertEquals(localDateTime.getSecond(), 0);
    }

    private long expected = 1481776200000L;
    private LocalDateTime localDateTime = LocalDateTime.of(2016, 12, 15, 15, 30, 0);

    @Test
    public void toLocalDateTime() {
        assertEquals(null, Dates2.toLocalDateTime((Date) null));

        assertEquals(localDateTime, Dates2.toLocalDateTime(new Date(expected)));
    }
}