package com.rooms.controller;

import com.rooms.exception.ObjectNotValidException;
import com.rooms.model.Response;
import com.rooms.model.Room;
import com.rooms.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Rest API for Room
 * @author Adijata
 *
 */
@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    /**
     * Get all rooms
     * @returns List of rooms
     */
    @GetMapping("/rooms")
    public Response<List<Room>> getAllRoom() {
    	Response<List<Room>> response = new Response<List<Room>>();
    	response.result = roomRepository.findAll();
    	response.message = HttpStatus.OK.toString();
    	return response;
    }
    
    @GetMapping("/rooms/{roomID}")
    public Room getRoom(@PathVariable  Long roomID) {
    	Room room =  roomRepository.findById(roomID).get();
    	
    	return room;
    }
    

    /**
     * Save room
     * @param room
     * @param errors
     * @return success message
     */
    @PostMapping("/rooms")
    public String createRooms(@Valid @RequestBody Room room, Errors errors) {
    	if (errors.hasErrors()) {
    		ObjectNotValidException ex = new ObjectNotValidException(errors);
            return ex.toString();
        }
    	
    	roomRepository.save(room);      
    	return HttpStatus.OK.toString();
    }
    
    /**
     * Update room
     * @param roomId
     * @param room
     * @param errors
     * @return success message
     */
    @PutMapping("/rooms/{roomId}")
    public String updateRooms(@PathVariable String roomId, @Valid @RequestBody Room room, Errors errors) {
    	if (errors.hasErrors()) {
    		ObjectNotValidException ex = new ObjectNotValidException(errors);
            return ex.toString();
        }
    	
    	roomRepository.save(room);      
    	return HttpStatus.OK.toString();
    }
    
    /**
     * Delete room by roomId
     * @param roomId
     * @return success
     */
    @DeleteMapping("/rooms/{roomId}")
    public Boolean deleteRoom(@PathVariable Long roomId)
    {
    	roomRepository.deleteById(roomId);
    	return true;
    }
}
