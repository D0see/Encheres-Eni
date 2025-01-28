package com.enchereseni.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class RegistrationController {

    private final JdbcTemplate jdbc;
    private final PasswordEncoder encoder;

    public RegistrationController(JdbcTemplate jdbc, PasswordEncoder encoder) {
        this.jdbc = jdbc;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String showLoginForm(Principal principal) {
        //System.out.println(principal.getName());
        return "login"; // Correspond à templates/login.html
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String pseudo,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String zipCode,
            @RequestParam String city) {

//        // Vérification pseudo unique
//        if(jdbc.queryForObject(
//                "SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = ?",
//                Integer.class, pseudo) > 0) {
//            return "redirect:/register?error";
//        }

        // Insertion utilisateur
            try {
                jdbc.update(
                        "INSERT INTO UTILISATEURS (pseudo, mot_de_passe, email, administrateur, nom, prenom, telephone, rue, code_postal, ville, credit) " +
                                "VALUES (?, ?, ?, 0, ?, ?, ?, ?, ?, ?, 100)", // Default empty values
                        pseudo,
                        encoder.encode(password),
                        email,
                        firstName,
                        lastName,
                        phone,
                        address,
                        zipCode,
                        city
                );
            } catch (DataAccessException e) {
                e.printStackTrace(); // Check logs for errors
                return "redirect:/register?error";
            }


        return "redirect:/login";
    }
}