package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Room;
import com.example.easynotes.repository.RoomRepository;
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
}
