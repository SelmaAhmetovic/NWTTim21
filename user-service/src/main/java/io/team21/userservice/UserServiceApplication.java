package io.team21.userservice;

import io.team21.userservice.controller.RoleController;
import io.team21.userservice.entity.Role;
import io.team21.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserServiceApplication.class, args);
/*		public final String topicExchangeName = "spring-boot-exchange";

		    public final String queueName = "spring-boot";

		    @Bean
		    Queue queue() {
		        return new Queue(queueName, false);
		    }

		    @Bean
		    TopicExchange exchange() {
		        return new TopicExchange(topicExchangeName);
		    }

		    @Bean
		    Binding binding(Queue queue, TopicExchange exchange) {
		        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
		    }

		    @Bean
		    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
		            MessageListenerAdapter listenerAdapter) {
		        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		        container.setConnectionFactory(connectionFactory);
		        container.setQueueNames(queueName);
		        container.setMessageListener(listenerAdapter);
		        return container;
		    }

		    @Bean
		    MessageListenerAdapter listenerAdapter(Receiver receiver) {
		        return new MessageListenerAdapter(receiver, "receiveMessage");
		    }

		    public static void main(String[] args) throws InterruptedException {
		        SpringApplication.run(Application.class, args).close();
		    }*/

	}

}
