package com.enchereseni.controller;


import com.enchereseni.bll.CategoryService;
import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


@Controller
//@RequestMapping("/encheres")
public class HomeController {

    private final ItemService itemService;
    private final UserService userService;
    private final CategoryService categoryService;

    public HomeController(UserService userService, ItemService itemService, CategoryService categoryService) {
        this.userService = userService;
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home (Model model) {
        return "redirect:/encheres";
    }

    @GetMapping("/encheres")
    public String encheres(Model model) {
        model.addAttribute("items",itemService.getItems());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "index";
    }


    //TO TEST
    @PostMapping("/encheres")
    public String filterEncheres(
            @RequestParam(name = "selectedFilters", required = false) List<String> selectedFilters,
            @RequestParam(name = "searchByName", required  = false) String searchByName,
            @RequestParam(name = "selectedCategory", required = true) String selectedCategory,
            Principal principal,
            Model model) {
        System.out.println("selectedFilters " + selectedFilters);
        System.out.println("searchByName " + searchByName);
        System.out.println("selectedCategory " + selectedCategory);
        var items = itemService.getItems();

        if (!selectedCategory.equals("Toutes")) {
            items = items.stream().filter(itemSold -> itemSold.getCategory().getWording().equals(selectedCategory)).toList();
        }
        if (!searchByName.trim().isEmpty()) {
            items = items.stream().filter(itemSold -> itemSold.getName().contains(searchByName)).toList();
        }
        if (selectedFilters != null) {
            if (selectedFilters.contains("Mes encheres")) {
                // items = items.stream().filter(itemSold -> itemSold.getAuctions().getUserName.equals(principal.getName()));
                // todo afficher les encheres de l'utilisateur en session
            }
            if (selectedFilters.contains("Encheres en cours")) {
                items = items.stream().filter(itemSold -> itemSold.getBeginningAuctionDate().isBefore(LocalDate.now())
                                                                && itemSold.getEndingAuctionDate().isAfter(LocalDate.now())).toList();
            }
            if (selectedFilters.contains("mesVentes")) {
               items = items.stream().filter(itemSold -> {
                   return itemSold.getUser().getUsername().equals(principal.getName());
               }).toList();
            }
        }
        model.addAttribute("items",items);
        return "index";
    }

}
