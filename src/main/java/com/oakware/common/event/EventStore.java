package com.oakware.common.event;

import java.util.List;

public interface EventStore {

    List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId);

    List<StoredEvent> allStoredEventsSince(long aStoredEventId);

    List<StoredEvent> findRecent(int maxCount);

    StoredEvent append(DomainEvent aDomainEvent);

    long countStoredEvents();
}
