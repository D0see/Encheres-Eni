package com.enchereseni.dal;

import com.enchereseni.bo.User;

import java.util.List;

public interface UserDAO {

    void create(User user);
    List<User> readAll();
    User read(int id);
    void update(User user);
    void delete(int id);
}