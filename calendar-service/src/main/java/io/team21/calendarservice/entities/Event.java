package io.team21.calendarservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    @NotNull(message = "Event title must be specified.")
    @Size(min = 3, max = 50)
    private String title;

    private LocalDateTime start;
    
    private LocalDateTime end;

    private Integer roomId;

    
   
    private Integer userId;
    
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    
    public int getEventId() {
        return this.eventId;
    }
    

  public String getTitle() {
        return title;
    }    

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public int getUserId() {
        return this.userId;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    
    public LocalDateTime getEnd() {
        return this.end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    protected Event() {
    }

    private Event(String title, LocalDateTime start, LocalDateTime end, Integer roomId, Integer userId) {
        this.setTitle(title);
        this.setRoomId(roomId);
        this.setStart(start);
        this.setEnd(end);
        this.setUserId(userId);
    }

}
