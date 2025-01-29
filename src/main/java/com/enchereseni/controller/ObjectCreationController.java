package com.enchereseni.controller;

import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.sql.CommonDataSource;

@Controller
public class ObjectCreationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CommonDataSource commonDataSource;


    @GetMapping("/vendre")
    public String creerObjet(Principal principal,Model model) {
        model.addAttribute("itemSold", new ItemSold());

        String userConnected =principal.getName();
        userService.getUsers().stream().filter(user -> userConnected.equals(user.getUsername())).findFirst().ifPresent(user -> {

            model.addAttribute("user", user);
        });
        System.out.println(userConnected);
        System.out.println(model.getAttribute("user"));



        return "itemCreation";
    }

    @PostMapping("/vendre")
    public String createItem(@ModelAttribute ItemSold itemSold,Principal principal,Model model ) {

userService.getUserbyUsername(principal.getName());


        System.out.println(itemSold.getName());
        System.out.println(itemSold.getDescription());
        System.out.println(itemSold.getFirstPrice());
        System.out.println(itemSold.getBeginningAuctionDate());
        System.out.println("ItemSold user: " + itemSold.getUser());
        System.out.println("pase por aca");
        itemService.createItem(itemSold);

        return "redirect:/home";

    }
}
