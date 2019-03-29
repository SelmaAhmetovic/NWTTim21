package io.team21.calendarservice.dao;

import io.team21.calendarservice.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarDao extends JpaRepository<Calendar, Integer> {
}
