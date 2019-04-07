package io.team21.calendarservice.exceptions;

public class EventNotFoundException extends RuntimeException {
    EventNotFoundException(Integer id) {
        super("Could not find event " + id);
    }
}
