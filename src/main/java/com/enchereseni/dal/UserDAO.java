package com.enchereseni.dal;

import com.enchereseni.bo.User;

import java.util.List;

public interface UserDAO {

    List<User> read();
    User read(int id);
    void update(User user);
    void delete(User user);
    void delete(int id);
}
