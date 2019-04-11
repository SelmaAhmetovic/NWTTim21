package com.calendar.calendarapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CalendarappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarappApplication.class, args);
	}

}
