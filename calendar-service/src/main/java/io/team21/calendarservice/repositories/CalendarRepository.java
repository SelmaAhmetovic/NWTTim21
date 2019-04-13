package io.team21.calendarservice.repositories;

import io.team21.calendarservice.entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
}
