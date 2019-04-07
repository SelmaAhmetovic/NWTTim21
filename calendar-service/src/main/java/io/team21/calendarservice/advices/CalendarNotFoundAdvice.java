package io.team21.calendarservice.advices;

import io.team21.calendarservice.exceptions.CalendarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CalendarNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CalendarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CalendarNotFoundHandler(CalendarNotFoundException ex) {
        return ex.getMessage();
    }
}
