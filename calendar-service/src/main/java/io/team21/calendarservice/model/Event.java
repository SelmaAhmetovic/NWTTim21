package io.team21.calendarservice.model;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String name;
    private String location;
    private LocalDateTime time;
    private String days;
    private Calendar calendar;
    private int RoomId;

    public Event() {

    }
}