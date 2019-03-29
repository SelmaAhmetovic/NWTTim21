package io.team21.calendarservice.dao;

import io.team21.calendarservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<Event, Integer> {
}
