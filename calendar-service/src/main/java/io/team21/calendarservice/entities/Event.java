package io.team21.calendarservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Event name must be specified.")
    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 1, max = 50, message = "Location must be between 1 and 50 characters long")
    private String location;

    @NotNull(message = "Time must be specified.")
    private LocalTime time;

    @NotNull(message = "At least one day of the week must be specified.")
    @Size(min = 1, max = 15)
    private String days;

    @Range(min = 1, max = 15, message = "Room must be between 1 and 15 characters long.")
    private Integer room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "calendar_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Calendar calendar;

    protected Event() {
    }

    private Event(String name, String location, LocalTime time, String days, Integer room, Calendar calendar) {
        this.setName(name);
        this.setLocation(location);
        this.setTime(time);
        this.setDays(days);
        this.setRoom(room);
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
