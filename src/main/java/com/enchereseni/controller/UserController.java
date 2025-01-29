package com.enchereseni.controller;

import com.enchereseni.bll.UserService;
import com.enchereseni.bo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
        model.addAttribute("isUserInSession", userName.equals(principal.getName()));
        var targetUser = userService.getUsers().stream().filter(user -> user.getUsername().equals(userName)).findAny();
        targetUser.ifPresent(user -> {
            model.addAttribute("user", user);
        });
        return "affichageUser";
    }

    @PostMapping("/user")
    public String modifyUser(User user, Principal principal, HttpServletRequest request) {
        userService.getUsers().stream().filter(user1 -> user1.getUsername().equals(user.getUsername())).findFirst().ifPresent(user1 -> {
            user.setUserID(user1.getUserID());
        });
        System.out.println(user.getPseudo() + " newPseudo");
        System.out.println(userService.isUnique(user) + " isUnique");
        if (!principal.getName().equals(user.getUsername())  && !userService.getUserbyUsername(principal.getName()).getEmail().equals(user.getEmail()) && !userService.isUnique(user)) {
            System.out.println("NOT UNIQUE MUDAFAKA");
            return "redirect:/user/" + principal.getName();
        }
        userService.update(user);
        if (!principal.getName().equals(user.getUsername())) {
            User updatedUserDetails = userService.getUserbyUsername(user.getUsername());
            Authentication newAuth = new UsernamePasswordAuthenticationToken(
                    updatedUserDetails,
                    updatedUserDetails.getPassword(),
                    updatedUserDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            }
        }
        System.out.println("user updated");
        return "redirect:/user/" + user.getUsername();
    }

    @PostMapping("/deleteMyAccount")
    public String deleteMyAccount(Principal principal, HttpServletRequest request) {
        System.out.println("delete my account");
        userService.getUsers().stream().filter(user1 -> user1.getUsername().equals(principal.getName())).findFirst().ifPresent(user1 -> {
            userService.removeUser(user1.getUserID());
        });
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}
