package com.controllers;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.models.Event;

@RestController
public class InitializeEventController {

	  private final RabbitTemplate rabbitTemplate;

	  private final Exchange exchange;

	  public InitializeEventController(RabbitTemplate rabbitTemplate, Exchange eventExchange) {
	    this.rabbitTemplate = rabbitTemplate;
	    this.exchange = eventExchange;
	  }
	  	  
	  @Async
	  @RequestMapping("/scheduleEvent/{id}")
	  public String ScheduleEvent(@PathVariable Integer id) {
		  
		String routingKey = "event.created";
		rabbitTemplate.convertAndSend(exchange.getName(), routingKey, id.toString());
		return  HttpStatus.OK.toString();
	  }
}
