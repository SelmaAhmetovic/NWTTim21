package io.team21.calendarservice.controllers;

import io.team21.calendarservice.entities.Calendar;
import io.team21.calendarservice.repositories.CalendarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CalendarController {
    private final CalendarRepository repository;

    CalendarController(CalendarRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Calendar> getCalendars() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Calendar> createCalendar(@Valid @RequestBody Calendar calendar) {
        repository.save(calendar);
        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }
}
