package io.team21.userservice.controller;

import io.team21.userservice.entity.Role;
import io.team21.userservice.model.RoleModel;
import io.team21.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    //@CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping(value = "/allRoles", method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    //@CrossOrigin(origins = "http://localhost:9000")
    //consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public Role addNewRole(@RequestBody RoleModel role) {
        Role entityModel = new Role();
        entityModel.setId(role.getId());
        entityModel.setName(role.getName());
        return this.roleService.addRole(entityModel);
    }


}