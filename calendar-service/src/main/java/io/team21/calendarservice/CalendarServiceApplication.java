package io.team21.calendarservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalendarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner demo(CalendarRepository repository) {
//        return (args) -> {
//            // save a couple of calendars
//            repository.save(new Calendar(1, "First"));
//            repository.save(new Calendar(2));
//        };
//    }
}
