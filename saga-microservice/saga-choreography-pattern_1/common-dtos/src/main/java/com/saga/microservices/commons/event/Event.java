package com.saga.microservices.commons.event;

import java.util.UUID;
import java.util.Date;

public interface Event {
    UUID getEventId();
    Date getDate();
}
