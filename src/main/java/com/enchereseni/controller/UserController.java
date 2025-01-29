package com.enchereseni.controller;

import com.enchereseni.bll.UserService;
import com.enchereseni.bo.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@Controller
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{name}")
    public String showUser(@PathVariable("name") String userName, Model model, Principal principal) {
        System.out.println("Get user: " + userName);
        model.addAttribute("isUserInSession", userName.equals(principal.getName()));
        var targetUser = userService.getUsers().stream().filter(user -> user.getUsername().equals(userName)).findAny();
        targetUser.ifPresent(user -> {
            model.addAttribute("user", user);
            System.out.println(user.getAddress());
            System.out.println(user.getEmail());
        });
        System.out.println(model.getAttribute("user"));
        return "affichageUser";
    }

    @PostMapping("/user")
    public String modifyUser(User user, Principal principal, Model model) {
        System.out.println("update user");
        System.out.println("user: " + user);
        System.out.println(user.getUserID());
        System.out.println(user.getUsername());
        userService.getUsers().stream().filter(user1 -> user1.getUsername().equals(user.getUsername())).findFirst().ifPresent(user1 -> {
            user.setUserID(user1.getUserID());
        });
        if (!userService.isUnique(user)) {
            System.out.println("NOT UNIQUE MUDAFAKA");
            return "redirect:/user/" + principal.getName();
        }
        userService.update(user);
        System.out.println("user updated");
        return "redirect:/user/" + principal.getName();
    }
}
