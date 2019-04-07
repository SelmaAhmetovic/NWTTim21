package io.team21.calendarservice.repositories;

import io.team21.calendarservice.entities.Calendar;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<Calendar, Integer> {
}
