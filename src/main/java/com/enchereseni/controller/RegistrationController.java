package com.enchereseni.controller;

import com.enchereseni.bll.UserService;
import com.enchereseni.bo.User;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.security.Principal;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {

    private final View error;
    private UserService userService;

    public RegistrationController(UserService userService, View error) {
        this.userService = userService;
        this.error = error;
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



    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, Model model, BindingResult result) {
        System.out.println(user.getEmail());
        if (result.hasErrors()) {
            model.addAttribute("error", "Une erreur est survenue pendant l'inscription");
            return "register";
        }
        String regexPseudo="^[a-zA-Z0-9_-]+$";
        if (!Pattern.matches(regexPseudo, user.getPseudo())) {
            model.addAttribute("error", "Le pseudo doit être uniquement composé de lettres et de chiffres.");
            System.out.println(user.getPseudo());
            return "register";
        }
        if (!userService.isUnique(user)) {return "redirect:/register?nonUniqueUser";}
        try {
            userService.createUser(user);
        } catch (DataAccessException e) {
            e.printStackTrace();// Check logs for errors
            model.addAttribute("error", "Une erreur est survenue lors de l'enregistrement. Veuillez réessayer.");

            return "redirect:/register?error";
        }
        return "redirect:/login";

    }
    //pour validation
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model) {
            List<FieldError> errors = ex.getBindingResult().getFieldErrors();
            model.addAttribute("errors", errors);
            return "errorPage";  // Affiche une page d'erreur avec les messages normalement
        }
    }
}