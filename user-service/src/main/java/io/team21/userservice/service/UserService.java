package io.team21.userservice.service;
import io.team21.userservice.entity.User;
import io.team21.userservice.dao.UserDao;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }

    public User addUser(User user) {
        return this.userDao.save(user);
    }

    // public User deleteUser(User user) { return this.userDao.delete(user); }
}


//other methods omitted for brevity

