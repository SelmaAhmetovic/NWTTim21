package io.team21.userservice.controller;

import io.team21.userservice.entity.User;
import io.team21.userservice.model.UserModel;
import io.team21.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value="Users", description="Users CRUD")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
//            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/getUser/{userId}", method= RequestMethod.GET)
    public User getUserById(@PathVariable("userId") int userId) {
        User user = userService.findOneUser(userId);
        return user;
    }

    @ApiOperation(value = "Add an user")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public UserModel addNewUser(
            @ApiParam(value = "User object store in database table", required = true)
            @RequestBody UserModel user) {
        return this.userService.addUser(user);
    }

    @RequestMapping(value = "/updateUser/{userId}", method= RequestMethod.PUT)
    public String updateUser(@RequestBody UserModel user, @PathVariable int userId) {
        UserModel userById = userService.findOneUserModel(userId);
        user.setId(userId);
        userService.addUser(user);
        return "User is successfully updated";
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable("userId") int userId) {
        String status1 = userService.deteleUserById(userId);
        return status1;
    }

}
