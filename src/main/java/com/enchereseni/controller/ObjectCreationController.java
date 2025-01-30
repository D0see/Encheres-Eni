package com.enchereseni.controller;

import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import jakarta.validation.Valid;
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

        return "redirect:/";

    @PostMapping("/creerObjet")
    public String createItem(@Valid @ModelAttribute("item") ItemSold item, Principal principal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "register";
        }
        if (principal != null) {
            // Il y a un membre en session
            if (result.hasErrors()) {
                // Il y a des erreurs sur le formulaire
                return "objectCreationForm";
            } else {
                System.out.println(item);
                // itemService.creerItem(item);
                return "redirect:/";
            }
        } else {
            System.out.println("Aucun membre en session");
            return "redirect:/";
        }
    }
}
