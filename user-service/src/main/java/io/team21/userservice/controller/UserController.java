package io.team21.userservice.controller;

import io.team21.userservice.entity.User;
import io.team21.userservice.model.UserModel;
import io.team21.userservice.service.UserService;
import io.team21.userservice.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value="Users", description="Operations pertaining to users in College schedule maker")
public class UserController {
    @Autowired
    UserService userService;

    //GET
    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of users"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }


    //GET BY ID
    @ApiOperation(value = "View an user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/getUser/{userId}", method= RequestMethod.GET)
    public ResponseEntity<User> getUserById(
            @ApiParam(value = "User id from which user object will retrieve", required = true)
            @PathVariable("userId") int userId)
            throws ResourceNotFoundException {
        User user = userService.findOneUser(userId);
        //.orElseThrow(() - > new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    //POST
    @ApiOperation(value = "Add an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added an user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public UserModel addNewUser(
            @ApiParam(value = "Store an user object in database table", required = true)
            @Valid @RequestBody UserModel user) {
        return this.userService.addUser(user);
    }



    //PUT
    @ApiOperation(value = "Update an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated an user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/updateUser/{userId}", method= RequestMethod.PUT)
    public ResponseEntity <UserModel> updateUser(
            @ApiParam(value = "Update an role object in database table", required = true)
            @Valid @RequestBody UserModel user,
            @PathVariable int userId)
            throws ResourceNotFoundException {
        UserModel userById = userService.findOneUserModel(userId);
        //.orElseThrow(() - > new ResourceNotFoundException("User not found for this id :: " + userId));
        user.setId(userId);
        userService.addUser(user);
        return ResponseEntity.ok(userById);
       /* UserModel userById = userService.findOneUserModel(userId);
        user.setId(userId);
        userService.addUser(user);
        return "User is successfully updated";*/
    }

    //DELETE
    @ApiOperation(value = "Delete an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted an user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
    public Map < String, Boolean > deleteUserById (
            @ApiParam(value = "Delete an user object from database table", required = true)
            @PathVariable("userId") int userId)
            throws ResourceNotFoundException
    {
        UserModel userById = userService.findOneUserModel(userId);
        //.orElseThrow(() - > new ResourceNotFoundException("User not found for this id :: " + userId));
        String status1 = userService.deteleUserById(userId);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
