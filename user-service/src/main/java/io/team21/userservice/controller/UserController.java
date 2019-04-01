package io.team21.userservice.controller;

import io.team21.userservice.entity.User;
import io.team21.userservice.model.UserModel;
import io.team21.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //@CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    //@CrossOrigin(origins = "http://localhost:9000")
    //consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public UserModel addNewUser(@RequestBody UserModel user) {
        return this.userService.addUser(user);
    }

    /*@CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public UserModel deleteUser(@RequestBody UserModel user) {
        return this.userService.deleteUser(user);
    }*/

    //other controllers omitted for brevity

}
