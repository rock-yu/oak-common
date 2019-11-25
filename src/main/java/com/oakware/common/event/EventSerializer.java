package com.oakware.common.event;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class EventSerializer extends AbstractSerializer {

    private static EventSerializer eventSerializer;

    public static synchronized EventSerializer instance() {
        if (EventSerializer.eventSerializer == null) {
            EventSerializer.eventSerializer = new EventSerializer();
        }

        return EventSerializer.eventSerializer;
    }

    private EventSerializer() {
        super();
    }

    public String serialize(DomainEvent aDomainEvent) {
        try {
            String serialization = this.objectMapper().writeValueAsString(aDomainEvent);
            return serialization;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends DomainEvent> T deserialize(String aSerialization, final Class<T> aType) {
        try {
            T domainEvent = this.objectMapper().readValue(aSerialization, aType);
            return domainEvent;
        } catch (IOException e) {
            throw new RuntimeException("fail to read json: '" + aSerialization + "'", e);
        }
    }

    public <T extends DomainEvent> T deserializeRecordingRequested(String aSerialization, final Class<T> aType) {
        try {
            T domainEvent = this.objectMapper().readValue(aSerialization, aType);
            return domainEvent;
        } catch (IOException e) {
            throw new RuntimeException("fail to read json: '" + aSerialization + "'", e);
        }
    }

    public String serializeRecordingRequested(DomainEvent aDomainEvent) {
        try {
            String serialization = this.objectMapper().writeValueAsString(aDomainEvent);
            return serialization;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("fail to write json: '" + aDomainEvent + "'", e);
        }
    }

}
