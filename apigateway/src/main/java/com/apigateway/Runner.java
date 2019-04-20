package com.apigateway;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

   private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
    	for (;;) {
	        System.out.println("Sending message...");
	        rabbitTemplate.convertAndSend("eventExchange", "reschedule", "Hello from RabbitMQ!");
	        Thread.sleep(1000000);
    	}
    }

}
