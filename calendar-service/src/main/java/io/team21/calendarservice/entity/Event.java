package io.team21.calendarservice.entity;

import io.team21.calendarservice.model.Calendar;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="events")
public class Event {
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;


    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "days")
    private String days;

    @Column(name = "CalendarId")
    private Calendar calendar;

    @Column(name = "RoomId")
    private int RoomId;

    public Event(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }
}
