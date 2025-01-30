package com.enchereseni.controller;

import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import com.enchereseni.dal.CategoryDAO;
import com.enchereseni.dal.CategoryDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.PropertyEditorSupport;
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
    @Autowired
    private CategoryDAO categoryDAO;






    @GetMapping("/vendre")
    public String creerObjet(Principal principal,Model model) {


        String userConnected =principal.getName();
        userService.getUsers().stream().filter(user -> userConnected.equals(user.getUsername())).findFirst().ifPresent(user -> {

            model.addAttribute("itemSold", new ItemSold());
            model.addAttribute("categories", itemService.getCategories());
            model.addAttribute("user", user);
        });



        return "itemCreation";
    }

    @PostMapping("/vendre")
    public String createItem( @ModelAttribute ItemSold itemSold,Principal principal,BindingResult bindingResult,Model model ) {

        User user=userService.getUserbyUsername(principal.getName());
        itemSold.setUser(user);

        int noCategorie = itemSold.getCategory().getCategory();
        Category category = categoryDAO.getCategoryById(noCategorie);
        itemSold.setCategory(category);

        itemService.createItem(itemSold);
        System.out.println(itemSold.getUser().getPhone());
        System.out.println(itemSold.getCategory().getCategory());
        System.out.printf(itemSold.getCategory().getWording());
        return "redirect:/";

    }
}
