package com.enchereseni.bll;

import com.enchereseni.bo.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    void removeUser(int id);

    List<User> getUsers();
    User getUserbyID(int id);

    void update (User user);

}
