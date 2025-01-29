package com.enchereseni.controller;

import com.enchereseni.bll.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String me(Principal principal, Model model) {
        var inSessionName = principal.getName();
        userService.getUsers().stream().filter(user -> user.getUsername().equals(inSessionName)).findFirst().ifPresent(user -> {
            model.addAttribute("user", user);
        });
        return "affichageUser";
    }

}
