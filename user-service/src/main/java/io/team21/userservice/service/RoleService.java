package io.team21.userservice.service;

import io.team21.userservice.dao.RoleDao;
import io.team21.userservice.entity.Role;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public List<Role> getAllRoles() {
        return this.roleDao.findAll();
    }

    public Role findOneRole(int roleId) {
        try{
            return this.roleDao.getOne(roleId);
        }catch (EntityNotFoundException ex){
            //dodati logiku za error
            return  null;
        }
    }

    public String deleteRoleById(int roleId) {
        this.roleDao.deleteRoleById(roleId);
        return "Role is successfully deleted";
    }

    public Role addRole(Role role) {
        return this.roleDao.save(role);
    }

    public Role updateRole(Role role) {
        Role tmp = findOneRole(role.getId());
        if(tmp != null){
            return this.roleDao.save(role);
        }
        return  null;
    }



    public int countRoles() {
        List<Role> roles = this.roleDao.findAll();
        if(roles == null) {
            return 0;
        }
        return roles.size();
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}


//other methods omitted for brevity

