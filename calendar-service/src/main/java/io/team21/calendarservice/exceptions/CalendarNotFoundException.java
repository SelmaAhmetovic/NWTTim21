package io.team21.calendarservice.exceptions;

public class CalendarNotFoundException extends RuntimeException {
    CalendarNotFoundException(Integer id) {
        super("Could not find calendar " + id);
    }
}
