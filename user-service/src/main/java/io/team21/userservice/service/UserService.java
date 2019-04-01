package io.team21.userservice.service;
import io.team21.userservice.dao.RoleDao;
//import io.team21.userservice.dao.UserRoleDao;
import io.team21.userservice.entity.Role;
import io.team21.userservice.entity.User;
import io.team21.userservice.dao.UserDao;
//import io.team21.userservice.entity.UserRole;
import io.team21.userservice.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    public List<UserModel> getAllUsers() {
        List<User> users = this.userDao.findAll();

        List<UserModel> returnUsers = new ArrayList<UserModel>();
        for (int i=0; i < users.size(); i++) {
            UserModel tmp = new UserModel();
            tmp.setFirstName(users.get(i).getFirstName());
            tmp.setLastName(users.get(i).getLastName());
            tmp.setId(users.get(i).getId());
            tmp.setUserName(users.get(i).getUserName());
            tmp.setPassword(users.get(i).getPassword());
            tmp.setRoleNames(users.get(i).getRoles().stream().map(Role::getName).collect(Collectors.toList()));

            returnUsers.add(tmp);
        }
        return returnUsers;
    }

    public UserModel addUser(UserModel user) {
        User tmpUser = new User();
        tmpUser.setFirstName(user.getFirstName());
        tmpUser.setLastName(user.getLastName());
        tmpUser.setPassword(user.getPassword());
        tmpUser.setUserName(user.getUserName());

        try{
            //TODO: provjeriti da li postoje role u bazi
            tmpUser.setRoles(roleDao.findByName(user.getRoleNames()));

            User tmp = this.userDao.save(tmpUser);
            user.setId(tmp.getId());

            return user;
        }catch (Exception e){
            return  null;
        }
    }

    // public UserModel deleteUser(UserModel user) { return this.userDao.delete(user); }
}


//other methods omitted for brevity

