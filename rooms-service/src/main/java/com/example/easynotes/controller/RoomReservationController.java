package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.RoomReservation;
import com.example.easynotes.repository.RoomReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
}
