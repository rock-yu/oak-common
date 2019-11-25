package com.oakware.common.event;

import java.util.Date;

/**
 * A domain event is something that is unique, but does not have a lifecycle.
 * The identity may be explicit, for example the sequence number of a payment,
 * or it could be derived from various aspects of the event such as where, when and what
 * has happened.
 */
public interface DomainEvent<T> {

    int eventVersion();

    Date occurredOn();

    String friendlyText();

}
