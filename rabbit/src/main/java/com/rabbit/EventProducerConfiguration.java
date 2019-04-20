package com.rabbit;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.controllers.InitializeEventController;

@Profile("subscriber")
@Configuration
@EnableScheduling
public class EventProducerConfiguration {

	 @Bean
	 public Exchange eventExchange() {
	   return new TopicExchange("eventExchange");
	 }
	
	 @Bean
	 public InitializeEventController eventController(RabbitTemplate rabbitTemplate, Exchange eventExchange) {
	   return new InitializeEventController(rabbitTemplate, eventExchange);
	 }

}