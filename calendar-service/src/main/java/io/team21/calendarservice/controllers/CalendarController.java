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

@RestController
public class CalendarController {
    @Autowired
    private CalendarRepository repository;

    @GetMapping("/calendar")
    public List<Calendar> getCalendars() {
        return repository.findAll();
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<Optional> getCalendarById(@PathVariable Integer id) {
        Optional calendar = repository.findById(id);

        if (!calendar.isPresent()) {
            throw new RecordNotFoundException("No calendar with id " + id);
        }
        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }

    @PostMapping("/calendar")
    public ResponseEntity<Calendar> createCalendar(@Valid @RequestBody Calendar calendar) {
        repository.save(calendar);
        return new ResponseEntity<>(calendar, HttpStatus.CREATED);
    }

    @PutMapping("/calendar/{id}")
    public ResponseEntity<Calendar> updateCalendar(@PathVariable Integer id, @Valid @RequestBody Calendar newCalendar) {
        return repository.findById(id).map(calendar -> {
            calendar.setName(newCalendar.getName());
            repository.save(calendar);
            return new ResponseEntity<>(calendar, HttpStatus.OK);
        }).orElseThrow(() -> new RecordNotFoundException("No calendar with id" + id));
    }

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
