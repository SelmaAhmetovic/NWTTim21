package io.team21.calendarservice.controllers;

import io.team21.calendarservice.entities.Event;
import io.team21.calendarservice.exceptions.RecordNotFoundException;
import io.team21.calendarservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * REST API for Events
 * @author haris
 */
@RestController
public class EventController {
    @Autowired
    private EventRepository eventRepository;


    /**
     * Get all events
     * @return List of events
     */
    @GetMapping("/event")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    /**
     * Get all events for a calendar
     * @param calendarId ID of the calendar
     * @return A list of Events
     */
    @GetMapping("/event/{userId}")
    public List<Event> getAllEventsByCalendarId(@PathVariable Integer userId) {
        return eventRepository.findByUserId(userId);
    }

    /**
     * Create a new event
     * @param calendarId ID of the calendar
     * @param event Name, time and days are required. Location and room are optional.
     * @return The created event
     */
    @PostMapping("/event")
    public String  createEvent( @Valid @RequestBody Event event) {      
         eventRepository.save(event);       
         return HttpStatus.OK.toString();
    }

    /**
     * Delete an event
     * @param calendarId ID of the calendar
     * @param eventId ID of the event
     * @return Status message
     */
    @GetMapping("/event/delete/{eventId}")
    public String deleteEvent(            
            @PathVariable(value = "eventId") Integer eventId) {
        return eventRepository.findById(eventId).map(event -> {
            eventRepository.delete(event);
            return "Event deleted.";
        }).orElseThrow(() -> new RecordNotFoundException(
                "Even not found with event id" + eventId));
    }
}
