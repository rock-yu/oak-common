package com.oakware.common.util;

import java.time.LocalDate;
import java.util.Date;

public final class LocalDateRangeFactory {
    public static LocalDateRange oneMonthTillToday() {
        return new LocalDateRange(
                LocalDate.now().minusMonths(1), LocalDate.now());
    }

    public static LocalDateRange between(LocalDate from, LocalDate to) {
        return new LocalDateRange(from, to);
    }

    public static LocalDateRange between(Date from, Date to) {
        return new LocalDateRange(
                Dates2.toLocalDate(from),
                Dates2.toLocalDate(to));
    }


}
