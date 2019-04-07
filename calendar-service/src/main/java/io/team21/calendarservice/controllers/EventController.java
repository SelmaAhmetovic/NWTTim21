package io.team21.calendarservice.controllers;

import io.team21.calendarservice.entities.Event;
import io.team21.calendarservice.exceptions.RecordNotFoundException;
import io.team21.calendarservice.repositories.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    private final EventRepository repository;

    EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/event")
    public List<Event> getEvents() {
        return repository.findAll();
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Optional> getEventById(@PathVariable Integer id) {
        Optional event = repository.findById(id);

        if (!event.isPresent()) {
            throw new RecordNotFoundException("No event with id " + id);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        repository.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@Valid @RequestBody Event newEvent, @PathVariable Integer id) {
        Optional optionalEvent = repository.findById(id);

        if (!optionalEvent.isPresent()) {
            throw new RecordNotFoundException("No event with id " + id);
        }
        repository.save(newEvent);
        return new ResponseEntity<>(newEvent, HttpStatus.OK);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        Optional event = repository.findById(id);
        if (!event.isPresent()) {
            throw new RecordNotFoundException("No event with id " + id);
        }
        repository.deleteById(id);
        return new ResponseEntity<>("Event deleted", HttpStatus.OK);
    }
}
