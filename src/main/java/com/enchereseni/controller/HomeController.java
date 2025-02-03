package com.enchereseni.controller;


import com.enchereseni.bll.AuctionService;
import com.enchereseni.bll.CategoryService;
import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Auction;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@SessionAttributes("categories")
@Controller
//@RequestMapping("/encheres")
public class HomeController {

    private final ItemService itemService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final AuctionService auctionService;

    public HomeController(UserService userService, ItemService itemService, CategoryService categoryService, AuctionService auctionService) {
        this.userService = userService;
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String home (Model model) {

        return "redirect:/encheres";
    }

    @GetMapping("/encheres")
    public String encheres(Model model) {

        var items = itemService.getItems();
        //adds auctions to item
        items.forEach(
                item -> item.setAuctions(auctionService.getAllAuctions().stream().filter(
                        auction -> auction.getItemSold().getItemId() == item.getItemId()).toList())
        );
        items.get(0).getAuctions().forEach(auction -> System.out.println(auction.getAmount() + " " + auction.getUser().getUsername()));

        model.addAttribute("items", items);
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
            if (selectedFilters.contains("mesEncheres")) {
                var myListOfAuctions = auctionService.getAllAuctions().stream().filter(
                        auction -> auction.getUser().getUsername().equals(principal.getName())
                ).toList();
                items = myListOfAuctions.stream().map(Auction::getItemSold).toList();

                System.out.println("mes encheres " + items);
            }

            if (selectedFilters.contains("mesVentesNonDebutes")) {
                items = items.stream().filter(itemSold ->
                        itemSold.getUser().getUsername().equals(principal.getName()) && itemSold.getBeginningAuctionDate().isAfter(LocalDate.now())).toList();
            }

            if (selectedFilters.contains("mesVentesEnCours")) {
                items = items.stream().filter(itemSold -> itemSold.getBeginningAuctionDate().isBefore(LocalDate.now())
                                                                && itemSold.getEndingAuctionDate().isAfter(LocalDate.now())
                                                                && itemSold.getUser().getUsername().equals(principal.getName())).toList();
            }

            if (selectedFilters.contains("mesVentesFinalisees")) {
                items = items.stream().filter(itemSold ->
                        itemSold.getUser().getUsername().equals(principal.getName()) && itemSold.getEndingAuctionDate().isBefore(LocalDate.now())).toList();
            }

            if (selectedFilters.contains("mesVentes")) {
               items = items.stream().filter(itemSold -> itemSold.getUser().getUsername().equals(principal.getName())).toList();
            }

            if (selectedFilters.contains("encheresGagnees")) {
                //TO DO
            }

            if (selectedFilters.contains("encheresEnCours")) {
                items = items.stream().filter(itemSold -> itemSold.getBeginningAuctionDate().isBefore(LocalDate.now())
                        && itemSold.getEndingAuctionDate().isAfter(LocalDate.now())).toList();

            }

        }

        items.forEach(
                item -> item.setAuctions(auctionService.getAllAuctions().stream().filter(
                        auction -> auction.getItemSold().getItemId() == item.getItemId()).toList())
        );
        model.addAttribute("items",items);
        return "index";
    }

}
