package io.team21.calendarservice.controllers;

import io.team21.calendarservice.entities.Calendar;
import io.team21.calendarservice.exceptions.RecordNotFoundException;
import io.team21.calendarservice.repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * REST API for Calendar
 * @author haris
 */
@RestController
public class CalendarController {
    @Autowired
    private CalendarRepository repository;

    /**
     * Get all calendars
     * @return List of all Calendars
     */
    @GetMapping("/calendar")
    public List<Calendar> getCalendars() {
        return repository.findAll();
    }

    /**
     * Get a Calendar by ID
     * @param id The ID of the Calendar to be returned
     * @return The specified calendar
     */
    @GetMapping("/calendar/{id}")
    public ResponseEntity<Optional> getCalendarById(@PathVariable Integer id) {
        Optional calendar = repository.findById(id);

        if (!calendar.isPresent()) {
            throw new RecordNotFoundException("No calendar with id " + id);
        }
        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }

    /**
     * Create a new calendar
     * @param calendar User is required, name is optional
     * @return The created calendar
     */
    @PostMapping("/calendar")
    public ResponseEntity<Calendar> createCalendar(@Valid @RequestBody Calendar calendar) {
        repository.save(calendar);
        return new ResponseEntity<>(calendar, HttpStatus.CREATED);
    }

    /**
     * Update a calendar
     * @param id The id of the Calendar to be updated
     * @param newCalendar Name is required
     * @return The edited calendar
     */
    @PutMapping("/calendar/{id}")
    public ResponseEntity<Calendar> updateCalendar(@PathVariable Integer id, @Valid @RequestBody Calendar newCalendar) {
        return repository.findById(id).map(calendar -> {
            calendar.setName(newCalendar.getName());
            repository.save(calendar);
            return new ResponseEntity<>(calendar, HttpStatus.OK);
        }).orElseThrow(() -> new RecordNotFoundException("No calendar with id" + id));
    }

    /**
     * Delete a calendar
     * @param id The ID of the calendar to be deleted
     * @return Status message
     */
    @DeleteMapping("/calendar/{id}")
    public ResponseEntity<String> deleteCalendar(@PathVariable Integer id) {
        Optional optionalCalendar = repository.findById(id);

        if (!optionalCalendar.isPresent()) {
            throw new RecordNotFoundException("No calendar with id " + id);
        }
        repository.deleteById(id);
        return new ResponseEntity<>("Calendar deleted.", HttpStatus.OK);
    }
}
