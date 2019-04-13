package com.apigateway.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;

public class RoomReservation {
	 
    private Long id;    

    private int userId;

    private Date reservationTime;
    
    private Room room;
    
    public Long getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
    
    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Date getReservationTime() {
        return reservationTime;
    }	 
    
    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }	
    
}
