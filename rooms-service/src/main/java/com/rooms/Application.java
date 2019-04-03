package com.rooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring application for Rooms microservice
 * @author Adijata
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
