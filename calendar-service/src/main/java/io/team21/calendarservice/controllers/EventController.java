package io.team21.calendarservice.controllers;

import io.team21.calendarservice.entities.Event;
import io.team21.calendarservice.exceptions.RecordNotFoundException;
import io.team21.calendarservice.repositories.CalendarRepository;
import io.team21.calendarservice.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @GetMapping("/event")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Optional> getEventById(@PathVariable Integer id) {
        Optional event = eventRepository.findById(id);

        if (!event.isPresent()) {
            throw new RecordNotFoundException("No event with id " + id);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/calendar/{calendarId}/event")
    public List<Event> getAllEventsByCalendarId(@PathVariable Integer calendarId) {
        return eventRepository.findByCalendarId(calendarId);
    }

    @PostMapping("/calendar/{calendarId}/event")
    public Event createEvent(@PathVariable(value = "calendarId") Integer calendarId, @Valid @RequestBody Event event) {
        return calendarRepository.findById(calendarId).map(calendar -> {
            event.setCalendar(calendar);
            return eventRepository.save(event);
        }).orElseThrow(() -> new RecordNotFoundException("No calendar with id " + calendarId));
    }

    @PutMapping("/calendar/{calendarId}/event/{eventId}")
    public Event updateEvent(
            @PathVariable(value = "calendarId") Integer calendarId,
            @PathVariable(value = "eventId") Integer eventId,
            @Valid @RequestBody Event newEvent) {
        if (!calendarRepository.existsById(calendarId)) {
            throw new RecordNotFoundException("No Calendar with id " + calendarId);
        }

        return eventRepository.findById(eventId).map(event -> {
            event.setName(newEvent.getName());
            event.setLocation(newEvent.getLocation());
            event.setTime(newEvent.getTime());
            event.setDays(newEvent.getDays());
            event.setRoom(newEvent.getRoom());
            return eventRepository.save(event);
        }).orElseThrow(() -> new RecordNotFoundException("No Event with id " + eventId));
    }

    @DeleteMapping("/calendar/{calendarId}/event/{eventId}")
    public ResponseEntity<?> deleteEvent(
            @PathVariable(value = "calendarId") Integer calendarId,
            @PathVariable(value = "eventId") Integer eventId) {
        return eventRepository.findByIdAndCalendarId(eventId, calendarId).map(event -> {
            eventRepository.delete(event);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RecordNotFoundException(
                "Calendar not found with id " + calendarId + " and event id" + eventId));
    }
}
