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
    public String createAuctionForItem(@PathVariable("itemId") int itemId, Model model, Principal principal) {
        var auction = new Auction();
        var date = new Date();
        auction.setDate(date);
        auction.setItemSold(itemService.getItemById(itemId));
        auction.setUser(userService.getUserbyUsername(principal.getName()));
        auction.setAmount(999);
        auctionService.createAuction(auction);
        return "index";
    }
}
