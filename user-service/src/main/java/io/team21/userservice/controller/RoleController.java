package io.team21.userservice.controller;

import io.team21.userservice.entity.Role;
import io.team21.userservice.model.RoleModel;
import io.team21.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/allRoles", method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @RequestMapping(value = "/getRole/{roleId}", method= RequestMethod.GET)
    public Role getRoleById(@PathVariable("roleId") int roleId) {
        Role role = roleService.findOneRole(roleId);
        return role;
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public Role addNewRole(@RequestBody RoleModel role) {
        Role entityModel = new Role();
        entityModel.setId(role.getId());
        entityModel.setName(role.getName());
        return this.roleService.addRole(entityModel);
    }

    @RequestMapping(value = "/deleteRole/{roleId}", method= RequestMethod.DELETE)
    public String deleteRoleById(@PathVariable("roleId") int roleId) {
        String status1 = roleService.deleteRoleById(roleId);
        return status1;
    }

    @RequestMapping(value = "/updateRole/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable int id) {
        Role roleById = roleService.findOneRole(id);
        role.setId(id);
        roleService.addRole(role);
        return ResponseEntity.noContent().build();
    }


}