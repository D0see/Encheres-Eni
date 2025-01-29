package com.enchereseni.controller;


import com.enchereseni.bll.UserService;
import com.enchereseni.bo.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
//@RequestMapping("/encheres")
public class HomeController {


    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String Home (Model model) {
        var users = userService.getUsers();
        model.addAttribute("users", users);
        System.out.println(users);
        return "index";
    }


}
