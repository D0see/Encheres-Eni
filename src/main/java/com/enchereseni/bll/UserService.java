package com.enchereseni.bll;

import com.enchereseni.bo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    void createUser(User user);

    void removeUser(int id);

    List<User> getUsers();
    User getUserbyID(int id);
    User getUserbyUsername(String username);

    void update (User user);
    boolean isUnique(User user);

    @Transactional
    void creerUser(User user);
}
