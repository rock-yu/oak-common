package com.oakware.common.persistence;

import com.oakware.common.event.DomainEvent;
import com.oakware.common.event.EventSerializer;
import com.oakware.common.event.EventStore;
import com.oakware.common.event.StoredEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;


public class JdbcEventStore implements EventStore {
    private JdbcTemplate jdbcTemplate;
    private final String eventTableName;
    private StoredEventRowMapper rowMapper = new StoredEventRowMapper();


    public JdbcEventStore(JdbcTemplate jdbcTemplate) {
        this(jdbcTemplate, "STORED_EVENT");
    }

    public JdbcEventStore(JdbcTemplate jdbcTemplate, String eventTableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventTableName = eventTableName;
    }

    @Override
    public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId) {
        String sql =
            "select * from " + eventTableName + " where (event_id between ? and ?) order by event_id";

        return jdbcTemplate.query(sql, rowMapper,
                aLowStoredEventId, aHighStoredEventId);
    }

    @Override
    public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
        String sql =
            "select * from " + eventTableName + " where event_id > ? order by event_id";

        return jdbcTemplate.query(sql, rowMapper,
                aStoredEventId);
    }

    @Override
    public StoredEvent append(DomainEvent aDomainEvent) {
        String eventSerialization =
                EventSerializer.instance().serialize(aDomainEvent);

        StoredEvent storedEvent =
                new StoredEvent(
                        aDomainEvent.getClass().getName(),
                        aDomainEvent.occurredOn(),
                        eventSerialization);

        String sql =
            "insert into " + eventTableName + "(type_name, occurred_on, event_body) values (?,?,?)";

        jdbcTemplate.update(sql,
                storedEvent.typeName(),
                storedEvent.occurredOn(),
                storedEvent.eventBody());

        return storedEvent;
    }

    @Override
    public long countStoredEvents() {
        return jdbcTemplate.queryForObject("select count(*) from " + eventTableName, Long.class);
    }

    @Override
    public List<StoredEvent> findRecent(int maxCount) {
        String sql = format("select top (%s) * from " + eventTableName + " order by event_id desc", maxCount);
        return jdbcTemplate.query(sql, rowMapper);
    }

    private static class StoredEventRowMapper implements RowMapper<StoredEvent> {
        @Override
        public StoredEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new StoredEvent(
                    rs.getString("type_name"),
                    rs.getTimestamp("occurred_on"),
                    rs.getString("event_body"),
                    rs.getLong("event_id"));
        }
    }
}
