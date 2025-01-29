package com.enchereseni.controller;

import com.enchereseni.bll.UserService;
import com.enchereseni.bo.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class RegistrationController {

private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(Principal principal) {
        //System.out.println(principal.getName());
        return "login"; // Correspond à templates/login.html
    }

    @GetMapping("/register")
    public String registerForm(User user, Model model) {
        model.addAttribute("user", user);
        return "register";
    }


    // TO ADD -> VALIDATION
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        System.out.println(user.getEmail());
        if (!userService.isUnique(user)) {return "redirect:/register?nonUniqueUser";}
        try {
            userService.createUser(user);
        } catch (DataAccessException e) {
            e.printStackTrace(); // Check logs for errors
            return "redirect:/register?error";
        }
        return "redirect:/login";
    }
}