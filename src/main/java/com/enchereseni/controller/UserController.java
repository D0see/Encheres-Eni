package com.enchereseni.controller;

import com.enchereseni.bll.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
@Controller
public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{name}")
    public String showUser(@PathVariable("name") String userName, Model model, Principal principal) {
        model.addAttribute("isUserInSession", userName.equals(principal.getName()));
        userService.getUsers().stream().filter(user -> user.getUsername().equals(userName)).findFirst().ifPresent(user -> {
            model.addAttribute("user", user);
        });
        return "affichageUser";
    }
}
