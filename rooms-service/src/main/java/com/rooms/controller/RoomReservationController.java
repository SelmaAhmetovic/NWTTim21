package com.rooms.controller;

import com.rooms.exception.ObjectNotValidException;
import com.rooms.model.Response;
import com.rooms.model.Room;
import com.rooms.model.RoomReservation;
import com.rooms.repository.RoomReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Rest API for RoomReservation
 * @author Adijata
 *
 */
@RestController
@RequestMapping("/api")
public class RoomReservationController {

    @Autowired
    RoomReservationRepository roomReservationRepository;

    /**
     * Get all room reservations
     * @return list of reservations
     */
    @GetMapping("/reservations")
    public List<RoomReservation> getAllRoomReservations() {
        return roomReservationRepository.findAll();
    }

    /**
     * Saves reservation to database
     * @param reservation
     * @param errors
     * @return Success message
     */
    @PostMapping("/reservations")
    public Response<Boolean> createRoomReservation(@Valid @RequestBody RoomReservation reservation, Errors errors) {
    	
    	Response<Boolean> response = new Response<Boolean>();    	
    	
    	if (errors.hasErrors()) {
    		ObjectNotValidException ex = new ObjectNotValidException(errors);
    		response.result = false;
    		response.message = ex.toString();
        }
    	
    	roomReservationRepository.save(reservation);
    	response.result = true;
    	response.message = HttpStatus.OK.toString();
    	return response;
    	
    }
    
    /**
     * Updates existing room reservation
     * @param reservationId
     * @param reservation
     * @param errors
     * @return
     */
    @PutMapping("/reservations/{reservationId}")
    public String updateReservation(@PathVariable String reservationId, @Valid @RequestBody RoomReservation reservation,  Errors errors) {
    	if (errors.hasErrors()) {
    		ObjectNotValidException ex = new ObjectNotValidException(errors);
            return ex.toString();
        }
    	
    	roomReservationRepository.save(reservation);
    	return HttpStatus.OK.toString();
    }
    
    /**
     * Deletes reservation by reservationId
     * @param reservationId
     * @return success boolean
     */
    @DeleteMapping("/reservations/{reservationId}")
    public Boolean deleteReservation(@PathVariable Long reservationId)
    {
    	roomReservationRepository.deleteById(reservationId);
    	return true;
    }
    
    /**
     * gets reservations that happened on or before parameter reservationTime
     * @param reservationTime
     * @return list of room reservations
     */
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
