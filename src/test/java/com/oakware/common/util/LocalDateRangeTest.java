package com.oakware.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LocalDateRangeTest {
    @Test
    public void testIterator_normal() {
        LocalDate from = LocalDate.of(2011, 9, 1);
        LocalDate to = LocalDate.of(2011, 9, 4);
        Iterator<LocalDate> iter = new LocalDateRangeIterator(from, to);
        assertEquals(LocalDate.of(2011, 9, 1), iter.next());
        assertEquals(LocalDate.of(2011, 9, 2), iter.next());
        assertEquals(LocalDate.of(2011, 9, 3), iter.next());
        assertEquals(LocalDate.of(2011, 9, 4), iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testIterator_abnormal() {
        LocalDate from = LocalDate.of(2011, 9, 1);
        LocalDate to = LocalDate.of(2011, 8, 29); // to before from
        Iterator<LocalDate> iter = new LocalDateRangeIterator(from, to);
        assertFalse(iter.hasNext());

        try {
            iter.next();
            Assert.fail();
        } catch (NoSuchElementException ex) {
            // good, i'm expecting an error
        }
    }

    @Test
    public void testIterator_from_to_on_same_day() {
        LocalDate from = LocalDate.of(2011, 9, 1);
        LocalDate to = LocalDate.of(2011, 9, 1);
        Iterator<LocalDate> iter = new LocalDateRangeIterator(from, to);
        assertTrue(iter.hasNext());
        assertEquals(LocalDate.of(2011, 9, 1), iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void test_foreach_loop() {
        LocalDate from = LocalDate.of(2011, 9, 1);
        LocalDate to = LocalDate.of(2011, 9, 3);
        LocalDateRange range = LocalDateRangeFactory.between(from, to);

        List<LocalDate> retrieves = new java.util.ArrayList<>();
        for (LocalDate localDate : range) {
            retrieves.add(localDate);
        }
        assertEquals(3, retrieves.size());
        assertTrue(retrieves.get(0).equals(LocalDate.of(2011,9,1)));
        assertTrue(retrieves.get(1).equals(LocalDate.of(2011,9,2)));
        assertTrue(retrieves.get(2).equals(LocalDate.of(2011,9,3)));
    }
}