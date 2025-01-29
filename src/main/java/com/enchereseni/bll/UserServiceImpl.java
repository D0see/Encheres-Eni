package com.enchereseni.bll;

import com.enchereseni.bo.User;
import com.enchereseni.dal.UserDAO;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

   private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(User user) {
    userDAO.create(user);
    }

    @Override
    public void removeUser(int id) {
        userDAO.delete(id);

    }

    @Override
    public List<User> getUsers() {
        System.out.println(userDAO.readAll()+ "UserServiceImpl");
        return userDAO.readAll();

    }

    @Override
    public User getUserbyID(int id) {
        return userDAO.read(id);
    }

    @Override
    public User getUserbyUsername(String username) {
        return userDAO.readAll().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public void update(User user) {
userDAO.update(user);
    }

    @Override
    public boolean isUnique(User user) {
        if(userDAO.readAll().stream().anyMatch(user1 -> user.getPseudo().equals(user1.getPseudo()) || user1.getEmail().equals(user.getEmail()))) {
            return false;
        };
        return true;
    }
}
