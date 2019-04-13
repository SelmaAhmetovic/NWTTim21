package io.team21.calendarservice.repositories;

import io.team21.calendarservice.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByCalendarId(Integer calendarId);

    Optional<Event> findByIdAndCalendarId(Integer eventId, Integer calendarId);
}
