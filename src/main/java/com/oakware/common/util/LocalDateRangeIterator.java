package com.oakware.common.util;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LocalDateRangeIterator implements Iterator<LocalDate> {

	private LocalDate current;
	private final LocalDate toDate;

	public LocalDateRangeIterator(LocalDate fromDate, LocalDate toDate) {
		this.current = fromDate;
		this.toDate = toDate;
	}

	@Override
	public boolean hasNext() {
		return !current.isAfter(toDate);
	}

	@Override
	public LocalDate next() {
		if (current.isAfter(toDate)) {
			throw new NoSuchElementException();
		}

		LocalDate ret = LocalDate.from(current);
		current = current.plusDays(1);
		return ret;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
