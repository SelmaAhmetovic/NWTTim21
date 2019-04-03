package io.team21.userservice.service;

import io.team21.userservice.dao.RoleDao;
import io.team21.userservice.dao.UserDao;
import io.team21.userservice.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public List<Role> getAllRoles() {
        return this.roleDao.findAll();
    }

    public Role findOneRole(int roleId) {
        return this.roleDao.getOne(roleId);
    }

    public String deleteRoleById(int roleId) {
        this.roleDao.deleteRoleById(roleId);
        return "Role is successfully deleted";
    }

    public Role addRole(Role role) {
        return this.roleDao.save(role);
    }

    public int countRoles() {
        List<Role> roles = this.roleDao.findAll();
        if(roles == null) {
            return 0;
        }
        return roles.size();
    }

}


//other methods omitted for brevity

