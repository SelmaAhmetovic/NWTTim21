package io.team21.userservice.controller;

import io.team21.userservice.entity.User;
import io.team21.userservice.model.UserModel;
import io.team21.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public UserModel addNewUser(@RequestBody UserModel user) {
        return this.userService.addUser(user);
    }

    @RequestMapping(value = "/getUser/{userId}", method= RequestMethod.GET)
    public User getUserById(@PathVariable("userId") int userId) {
        User user = userService.findOneUser(userId);
        return user;
    }

    @RequestMapping(value = "/updateUser/{userId}", method= RequestMethod.PUT)
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user, @PathVariable int userId) {
        UserModel userById = userService.findOneUserModel(userId);
        user.setId(userId);
        userService.addUser(user);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable("userId") int userId) {
        String status1 = userService.deteleUserById(userId);
        return status1;
    }

}
