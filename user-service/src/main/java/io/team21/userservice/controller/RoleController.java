package io.team21.userservice.controller;

import io.team21.userservice.entity.Role;
import io.team21.userservice.model.RoleModel;
import io.team21.userservice.service.RoleService;
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
    @RequestMapping(value = "/allRoles", method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    //GET BY ID
    @ApiOperation(value = "View a role by id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/getRole/{roleId}", method= RequestMethod.GET)
    public Role getRoleById(
            @ApiParam(value = "Role id from which role object will retrieve", required = true)
            @PathVariable("roleId") int roleId) {
        Role role = roleService.findOneRole(roleId);
        return role;
    }

    //POST
    @ApiOperation(value = "Add a role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added a role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/addRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public Role addNewRole(
            @ApiParam(value = "Store a role object in database table", required = true)
            @RequestBody RoleModel role) {
        Role entityModel = new Role();
        entityModel.setId(role.getId());
        entityModel.setName(role.getName());
        return this.roleService.addRole(entityModel);
    }

    //PUT
    @ApiOperation(value = "Update a role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated a role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/updateRole/{id}", method= RequestMethod.PUT)
    public String updateRole(
            @ApiParam(value = "Update a role object in database table", required = true)
            @RequestBody Role role, @PathVariable int id) {
        Role roleById = roleService.findOneRole(id);
        role.setId(id);
        roleService.addRole(role);
        return "Role is successfully updated";
    }

    //DELETE
    @ApiOperation(value = "Delete a role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted a role"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/deleteRole/{roleId}", method= RequestMethod.DELETE)
    public String deleteRoleById(
            @ApiParam(value = "Delete a role object from database table", required = true)
            @PathVariable("roleId") int roleId) {
        String status1 = roleService.deleteRoleById(roleId);
        return status1;
    }



}