package com.oakware.common.util;

import com.oakware.common.Validate2;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

/**
 * Representing an immutable date range class
 */
public final class LocalDateRange implements Iterable<LocalDate> {
	private final LocalDate from;
	private final LocalDate to;

	public LocalDateRange(LocalDate from, LocalDate to) {
		Validate2.isTrue((from != null) && (to != null), "'fromDate' and 'toDate' are both required");
		Validate2.isTrue(!from.isAfter(to), "'fromDate' can not be later than toDate");
		this.from = from;
		this.to = to;
	}
	
	@Override
	public Iterator<LocalDate> iterator() {
		return new LocalDateRangeIterator(from, to);
	}

	@Override
	public String toString() {
		return "from: " + from + " to: " + to;
	}

	@Deprecated
	public LocalDate getFrom() {return this.from;}

	@Deprecated
	public LocalDate getTo() {return this.to;}

	public LocalDate from() {return this.from;}
	public LocalDate to() {return this.to;}

	public Date from2() {
		return Dates2.toDate(this.from);
	}

	public Date to2() {
		return Dates2.toDate(this.to);
	}

	public Date toNextDay2() {
		return Dates2.toDate(this.to.plusDays(1));
	}

	public LocalDateRange plusDays(int days) {
		return new LocalDateRange(from.plusDays(days), to.plusDays(days));
	}
	
	public LocalDateRange minusDays(int days) {
		return new LocalDateRange(from.minusDays(days), to.minusDays(days));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LocalDateRange that = (LocalDateRange) o;
		return Objects.equals(from, that.from) &&
				Objects.equals(to, that.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}
}
