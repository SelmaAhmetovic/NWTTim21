package com.rooms.controller;

import com.rooms.exception.ResourceNotFoundException;
import com.rooms.model.Room;
import com.rooms.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @PostMapping("/rooms")
    public Room createRooms(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }
    
    @PutMapping("/rooms/{roomId}")
    public Room updateRooms(@PathVariable String roomId, @Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }
    
    @DeleteMapping("/rooms/{roomId}")
    public Boolean deleteRoom(@PathVariable Long roomId)
    {
    	roomRepository.deleteById(roomId);
    	return true;
    }
}
