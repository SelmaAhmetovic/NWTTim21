package io.team21.userservice.service;

import io.team21.userservice.dao.RoleDao;
import io.team21.userservice.dao.UserRoleDao;
import io.team21.userservice.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;

    public String deleteUserRoleByRoleId(int roleId) {
        this.userRoleDao.deleteUserRoleByRoleId(roleId);
        return "UserRole is successfully deleted";
    }

}


//other methods omitted for brevity

