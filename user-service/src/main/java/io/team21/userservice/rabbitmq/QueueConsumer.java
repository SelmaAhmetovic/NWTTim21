package io.team21.userservice.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.team21.userservice.model.UserModel;
import io.team21.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {
    @Autowired
    UserService userService;
    public void receiveMessage(String message) {
        processMessage(message);
    }
    private String processMessage(String message) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            UserModel model = objectMapper.readValue(message, UserModel.class);
            this.userService.addUser(model);
            return "Message Received. User is sucessfully added.";
        } catch (Exception e) {
            return "Error";
        }

    }
}
