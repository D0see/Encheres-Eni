package com.enchereseni.controller;


import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
//@RequestMapping("/encheres")
public class HomeController {

    private final ItemService itemService;
    private final UserService userService;

    public HomeController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String Home (Model model) {
        model.addAttribute("items",itemService.getItems());
        return "index";
    }

}
