package io.team21.calendarservice;

import io.team21.calendarservice.entities.Calendar;
import io.team21.calendarservice.repositories.CalendarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class CalendarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CalendarRepository repository) {
        return (args) -> {
            // save a couple of calendars
            repository.save(new Calendar(1, "First"));
            repository.save(new Calendar(2));
        };
    }
}
