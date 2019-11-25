package com.oakware.common.persistence;

import com.oakware.common.event.DomainEvent;
import com.oakware.common.event.StoredEvent;
import com.oakware.common.event.TestEvent;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class JdbcEventStoreIntegrationTest extends AbstractJUnitIntegrationTest {

    private String filename = "2010-12-08_15-22-33_~rock~380~.wav";
    private DomainEvent testEvent;

    private int userId = 1;

    @Autowired
    private JdbcEventStore eventStore;

    @Before
    public void setUp() throws Exception {
        createTableAndData();

        this.testEvent = new TestEvent(filename, new Date(), "hello");
    }

    private void createTableAndData() {
        execute("drop table if exists STORED_EVENT");
        execute("create table STORED_EVENT (" +
                "  event_id int generated always as IDENTITY(START WITH 1)," +
                "  event_body varchar(500)," +
                "  occurred_on datetime," +
                "  type_name varchar(100)" +
                ")");
    }

    @Test
    @Rollback
    public void testPersist() {
        this.eventStore.append(testEvent);

        assertThat(this.eventStore.countStoredEvents(), is(1l));
        List<StoredEvent> events = this.eventStore.allStoredEventsSince(0);
        assertThat(events.size(), is(1));

        StoredEvent storedEvent = events.get(0);

        assertThat(storedEvent.typeName(), is("com.oakware.common.event.TestEvent"));
        assertThat(storedEvent.occurredOn(), is(notNullValue()));

        String eventBody = storedEvent.eventBody();
        assertThat(eventBody, is(notNullValue()));
    }

    @Test
    @Rollback
    public void testStoredEvent() {
        StoredEvent storedEvent = this.eventStore.append(testEvent);

        assertThat(storedEvent.typeName(), is("com.oakware.common.event.TestEvent"));
        assertThat(storedEvent.occurredOn(), is(notNullValue()));

        TestEvent event = storedEvent.toDomainEvent();
        assertThat(event.message(), is("hello"));
        assertThat(event.eventVersion(), is(2));
    }
}