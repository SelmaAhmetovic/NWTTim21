package io.team21.userservice.controller;

import io.team21.userservice.entity.Role;
import io.team21.userservice.exception.ObjectNotValidException;
import io.team21.userservice.model.Response;
import io.team21.userservice.model.RoleModel;
import io.team21.userservice.service.RoleService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api(value="Roles", description="Operations pertaining to roles in College schedule maker")
public class RoleController {
    @Autowired
    RoleService roleService;

    //GET
    @ApiOperation(value = "View a list of available roles", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of roles"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Response<List<Role>>> getAllRoles() {
        Response<List<Role>> resp = new Response<List<Role>>();
        resp.message = HttpStatus.OK.toString();
        resp.result = roleService.getAllRoles();
        return ResponseEntity.ok().body(resp);
    }

    //GET BY ID

    @ApiOperation(value = "View a role by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/{roleId}", method= RequestMethod.GET)
    public ResponseEntity<Response<Role>> getRoleById(
            @ApiParam(value = "Role id from which role object will retrieve", required = true)
            @PathVariable("roleId") int roleId)
             {
                 Response<Role> resp = new Response<Role>();

                 resp.message = HttpStatus.OK.toString();
                 resp.result = this.roleService.findOneRole(roleId);

                 return ResponseEntity.ok().body(resp);
            }

/*    @PathVariable("roleId") String roleId)
    {
        ObjectNotValidException ex = new ObjectNotValidException(errors);
        boolean checkIntegerType = ex.isNumeric(roleId);
        if(checkIntegerType) {
            int newRoleId = Integer.parseInt(roleId);
            Response<Role> resp = new Response<Role>();
            resp.message = HttpStatus.OK.toString();
            resp.result = this.roleService.findOneRole(newRoleId);
            return ResponseEntity.ok().body(resp);
        } else {
            resp.message = HttpStatus.NOT_FOUND.toString();
            resp.result = null;
            return ResponseEntity.ok().body(resp);
        }

    }*/


    //POST
    @ApiOperation(value = "Add a role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added a role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<Response<Role>> addNewRole(
            @ApiParam(value = "Store a role object in database table", required = true)
            @Valid @RequestBody Role role,  Errors errors) {
        Response<Role> resp = new Response<Role>();
        if (errors.hasErrors()) {
            ObjectNotValidException ex = new ObjectNotValidException(errors);
            resp.message = ex.toString();
            return ResponseEntity.badRequest().body(resp);
        }
        resp.message = HttpStatus.OK.toString();
        resp.result = this.roleService.addRole(role);
        return ResponseEntity.ok().body(resp);
    }

    //PUT
    @ApiOperation(value = "Update a role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Response<Role>> updateRole(
            @ApiParam(value = "Update a role object in database table", required = true)
            @PathVariable int id, @Valid @RequestBody Role role,  Errors errors) {
        Response<Role> resp = new Response<Role>();

        if (errors.hasErrors()) {
            ObjectNotValidException ex = new ObjectNotValidException(errors);
            resp.message = ex.toString();
            return ResponseEntity.badRequest().body(resp);
        }
        role.setId(id);
        role = this.roleService.updateRole(role);
        resp.result = role;
        if(role == null){
            resp.message = HttpStatus.NOT_FOUND.toString();
        }else{
            resp.message = HttpStatus.OK.toString();
        }
        return ResponseEntity.ok().body(resp);
    }

    //DELETE
    @ApiOperation(value = "Delete a role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted a role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/{roleId}", method= RequestMethod.DELETE)
    public ResponseEntity<Response<String>> deleteRoleById(
            @ApiParam(value = "Delete a role object from database table", required = true)
            @PathVariable("roleId") int roleId) {
        Response<String> resp = new Response<String>();
        resp.result = this.roleService.deleteRoleById(roleId);;
        resp.message = HttpStatus.OK.toString();
        return ResponseEntity.ok().body(resp);
    }

}