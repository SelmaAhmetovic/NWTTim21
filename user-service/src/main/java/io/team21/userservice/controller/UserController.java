package io.team21.userservice.controller;

import io.team21.userservice.entity.User;
import io.team21.userservice.exception.ObjectNotValidException;
import io.team21.userservice.model.Response;
import io.team21.userservice.model.UserModel;
import io.team21.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import javax.validation.Valid;

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
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Response<List<UserModel>>> getAllUsers() {
        Response<List<UserModel>> resp = new Response<List<UserModel>>();
        resp.message = HttpStatus.OK.toString();
        resp.result = userService.getAllUsers();
        return ResponseEntity.ok().body(resp);
    }


    //GET BY ID
    @ApiOperation(value = "View an user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved an user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/{userId}", method= RequestMethod.GET)
    public ResponseEntity<Response<UserModel>> getUserById(
            @ApiParam(value = "User id from which user object will retrieve", required = true)
            @PathVariable("userId") int userId) {
        Response<UserModel> resp = new Response<UserModel>();
        resp.message = HttpStatus.OK.toString();
        resp.result = this.userService.findOneUserModel(userId);
        return ResponseEntity.ok().body(resp);
    }

    //POST
    @ApiOperation(value = "Add an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added an user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<Response<UserModel>> addNewUser(
            @ApiParam(value = "Store an user object in database table", required = true)
            @Valid @RequestBody UserModel userModel, Errors errors) {
        Response<UserModel> resp = new Response<UserModel>();

        if (errors.hasErrors()) {
            ObjectNotValidException ex = new ObjectNotValidException(errors);
            resp.message = ex.toString();
            return ResponseEntity.badRequest().body(resp);
        }
        resp.message = HttpStatus.OK.toString();
        resp.result = this.userService.addUser(userModel);
        return ResponseEntity.ok().body(resp);

    }



    //PUT
    @ApiOperation(value = "Update an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated an user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/{userId}", method= RequestMethod.PUT)
    public ResponseEntity<Response<UserModel>> updateUser(
            @ApiParam(value = "Update an role object in database table", required = true)
            @PathVariable int userId,
            @Valid @RequestBody UserModel user, Errors errors) {
        Response<UserModel> resp = new Response<UserModel>();
        if (errors.hasErrors()) {
            ObjectNotValidException ex = new ObjectNotValidException(errors);
            resp.message = ex.toString();
            return ResponseEntity.badRequest().body(resp);
        }
        resp.message = HttpStatus.OK.toString();
        UserModel userModel= this.userService.findOneUserModel(userId);
        user.setId(userId);
        resp.result = this.userService.addUser(user);
        return ResponseEntity.ok().body(resp);

    }

    //DELETE
    @ApiOperation(value = "Delete an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted an user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response<String>> deleteUserById (
            @ApiParam(value = "Delete an user object from database table", required = true)
            @PathVariable("userId") int userId) {
        Response<String> resp = new Response<String>();
        resp.result = this.userService.deteleUserById(userId);;
        resp.message = HttpStatus.OK.toString();
        return ResponseEntity.ok().body(resp);
    }
}
