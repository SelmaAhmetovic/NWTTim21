package com.apigateway.dto;

import java.time.LocalDateTime;

public class Event {
    private Integer id;

    private String title;

    private LocalDateTime start;
    
    private LocalDateTime end;
    
    
    private Boolean weekly;

    private String days;
   
    private Integer roomId;    
    
    public Integer userId;
    

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
}
