package com.rooms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RoomReservation")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class RoomReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Please enter id")
    private Long userId;

    @NotNull
    private Date reservationTime;
    
    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
    
    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Date getReservationTime() {
        return reservationTime;
    }
    
    @ManyToOne
    private Room room;   
    
    public void setRoom(Room room) {
        this.room = room;
    }
}
