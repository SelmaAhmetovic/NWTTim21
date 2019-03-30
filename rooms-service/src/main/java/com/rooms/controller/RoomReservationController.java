package com.rooms.controller;

import com.rooms.exception.ResourceNotFoundException;
import com.rooms.model.Room;
import com.rooms.model.RoomReservation;
import com.rooms.repository.RoomReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomReservationController {

    @Autowired
    RoomReservationRepository roomReservationRepository;

    @GetMapping("/reservations")
    public List<RoomReservation> getAllRoomReservations() {
        return roomReservationRepository.findAll();
    }

    @PostMapping("/reservations")
    public RoomReservation createRoomReservation(@Valid @RequestBody RoomReservation reservation) {
    	return roomReservationRepository.save(reservation);
    }
    
    @PutMapping("/reservations/{reservationId}")
    public RoomReservation updateReservation(@PathVariable String reservationId, @Valid @RequestBody RoomReservation reservation) {
        return roomReservationRepository.save(reservation);
    }
    
    @DeleteMapping("/reservations/{reservationId}")
    public Boolean deleteReservation(@PathVariable Long reservationId)
    {
    	roomReservationRepository.deleteById(reservationId);
    	return true;
    }
    
    @GetMapping("/reservations/{reservationTime}")
    public List<RoomReservation> getReservation(@PathVariable String reservationTime)
    {
    	try {
    		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(reservationTime); 
    		return roomReservationRepository.getReservationsOnSelectedDate(date);
    	}
    	catch(Exception ex) {
    		return null;
    	}
    }
}
