package com.rooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**import org.springframework.cloud.client.discovery.EnableDiscoveryClient;*/
/**import org.springframework.data.jpa.repository.config.EnableJpaAuditing;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring application for Rooms microservice
 * @author Adijata
 *
 */
@SpringBootApplication
/**@EnableJpaAuditing*/
/**@EnableDiscoveryClient*/
@RestController
public class Application {
	
	@GetMapping("/message")
	public String getMessage() {
		return "test method";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
