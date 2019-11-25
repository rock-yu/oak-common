package com.oakware.common.event;

import java.util.Date;

public class TestEvent implements DomainEvent {

    private int eventVersion;
    private Date occurredOn;
    private String message;

    // default constructor
    protected TestEvent() {}

    public TestEvent(
            String filename,
            Date occurredOn,
            String message) {

        this.eventVersion = 2;
        this.occurredOn = occurredOn;
        this.message = message;
    }

    @Override
    public int eventVersion() {
        return eventVersion;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }

    @Override
    public String friendlyText() {
        return null;
    }

    public String message() {
        return message;
    }

}
