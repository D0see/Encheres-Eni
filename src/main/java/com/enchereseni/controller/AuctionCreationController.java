package com.enchereseni.controller;

import com.enchereseni.bll.AuctionService;
import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Auction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class AuctionCreationController {

    private final ItemService itemService;
    private final UserService userService;
    private AuctionService auctionService;

    public AuctionCreationController(AuctionService auctionService, ItemService itemService, UserService userService) {
        this.auctionService = auctionService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @PostMapping("/CreationEnchere/{itemId}")
    public String createAuctionForItem(@RequestParam("amount") int amount, @PathVariable("itemId") int itemId, Model model, Principal principal) {

        var maxBid = auctionService.getAuctionsByItem(itemService.getItemById(itemId)).stream().sorted((a, b) -> b.getAmount() - a.getAmount()).toList().get(0);
        System.out.println("maxBid = " + maxBid.getAmount());
        if (maxBid.getAmount() > amount) {
            System.out.println("amount inputted too low");
            return "redirect:/encheres";
        }
        var auction = new Auction();
        var date = new Date();
        auction.setDate(date);
        auction.setItemSold(itemService.getItemById(itemId));
        auction.setUser(userService.getUserbyUsername(principal.getName()));
        auction.setAmount(amount);
        if (auctionService.getAuctionsByItem(itemService.getItemById(itemId)).stream().anyMatch(temp -> temp.getUser().getUsername().equals(principal.getName()))) {
            auctionService.updateAuction(auction);
            System.out.println("updated auction");
        } else {
            auctionService.createAuction(auction);
            System.out.println("createdAuction");
        }

        return "redirect:/encheres";
    }
}
