package com.enchereseni.controller;

import com.enchereseni.bll.AuctionService;
import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Auction;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String createAuctionForItem(@RequestParam("amount") int amount, @PathVariable("itemId") int itemId, Model model, Principal principal, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "redirect:/AuctionCreation";
        }
        Auction maxBid = new Auction();
        ItemSold currItem = itemService.getItemById(itemId);
        User currUser = userService.getUserbyUsername(principal.getName());
        if (auctionService.getAuctionsByItem(currItem).isEmpty()) {
            maxBid.setAmount(currItem.getFirstPrice());
        } else {
            maxBid = auctionService.getAuctionsByItem(currItem).stream().sorted((a, b) -> b.getAmount() - a.getAmount()).toList().get(0);
        }
        if (maxBid.getUser() != null && maxBid.getUser().getUsername().equals(currUser.getUsername())) {
            System.out.println("you can't create auction while the user already has the highest bid");
            return "redirect:/encheres";
        }

        System.out.println("maxBid = " + maxBid.getAmount());
        if (maxBid.getAmount() >= amount) {
            System.out.println("amount inputted too low");
            return "redirect:/encheres";
        }

        if (amount > currUser.getCredit()) {
            System.out.println("balance too low");
            return "redirect:/encheres";
        }

        // debits & recredits
        currUser.setCredit(currUser.getCredit() - amount);
        userService.update(currUser);
        if (maxBid.getUser() != null) {
            maxBid.getUser().setCredit(maxBid.getUser().getCredit() + maxBid.getAmount());
            userService.update(maxBid.getUser());
        }

        var auction = new Auction();
        var date = new Date();
        auction.setDate(date);
        auction.setItemSold(itemService.getItemById(itemId));
        auction.setUser(currUser);
        auction.setAmount(amount);
        if (auctionService.getAuctionsByItem(currItem).stream().anyMatch(temp -> temp.getUser().getUsername().equals(principal.getName()))) {
            auctionService.updateAuction(auction);
            System.out.println("updated auction");
        } else {
            auctionService.createAuction(auction);
            System.out.println("createdAuction");
        }

        //currItem.setHighestBid(maxBid);

        return "redirect:/encheres";
    }
}
