package com.enchereseni.controller;

import com.enchereseni.bll.AuctionService;
import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Auction;
import com.enchereseni.bo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@Controller
public class UserController {

    private final UserService userService;
    private final AuctionService auctionService;
    private final ItemService itemService;

    UserController(UserService userService, AuctionService auctionService, ItemService itemService) {
        this.userService = userService;
        this.auctionService = auctionService;
        this.itemService = itemService;
    }

    @GetMapping("/user/{name}")
    public String showUser(@PathVariable("name") String userName, Model model, Principal principal) {
        model.addAttribute("isUserInSession", userName.equals(principal.getName()));
        var targetUser = userService.getUsers().stream().filter(user -> user.getUsername().equals(userName)).findAny();
        targetUser.ifPresent(user -> {
            model.addAttribute("user", user);
        });
        return "affichageUser";
    }

    @PostMapping("/user")
    public String modifyUser(User user, Principal principal, HttpServletRequest request) {
        userService.getUsers().stream().filter(user1 -> user1.getUsername().equals(user.getUsername())).findFirst().ifPresent(user1 -> {
            user.setUserID(user1.getUserID());
        });
        if ((!principal.getName().equals(user.getUsername()) && !userService.isUnique(user))  ||
            !userService.getUserbyUsername(principal.getName()).getEmail().equals(user.getEmail()) ) {
            return "redirect:/user/" + principal.getName();
        }
        userService.update(user);

        //if pseudo changes disconnects & reconnects the user to update principal (bad bad bad)
        if (!principal.getName().equals(user.getUsername())) {
            User updatedUserDetails = userService.getUserbyUsername(user.getUsername());
            Authentication newAuth = new UsernamePasswordAuthenticationToken(
                    updatedUserDetails,
                    updatedUserDetails.getPassword(),
                    updatedUserDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            }
        }
        return "redirect:/user/" + user.getUsername();
    }

    @PostMapping("/deleteMyAccount")
    public String deleteMyAccount(Principal principal, HttpServletRequest request) {
        System.out.println("delete my account");
        User userToDelete = userService.getUserbyUsername(principal.getName());

        auctionService.getAuctionsByUsername(userToDelete.getUsername()).forEach(auction -> {
            auction.setUser(userService.getUserbyUsername("erasedUser"));
        });

        itemService.getItems().forEach(item -> {

            //Version where i keep the auction after user deletion and assign it to erasedUSER

//            if (item.getUser().getUsername().equals(userToDelete.getUsername())) {
//                System.out.println("this is my item im going to re-assign " + item.getName());
//                var erasedUser = userService.getUserbyUsername("erasedUser");
//                item.setUser(erasedUser);
//                System.out.println(erasedUser.getUsername() + " " + item.getName());
//                itemService.updateItem(item);
//            }

            if (item.getUser().getUsername().equals(userToDelete.getUsername())) {
                var highestBid = new Auction();
                if (!auctionService.getAuctionsByItem(item).isEmpty()) {
                    highestBid = auctionService.getAuctionsByItem(item).stream().filter(auction ->
                            auction.getItemSold().getItemId() == item.getItemId()).sorted((a, b) -> b.getAmount() - a.getAmount()).toList().get(0);
                }
                var highestBidder = highestBid.getUser();

                if (highestBidder != null && highestBidder.getUsername() != null) {
                    highestBidder.setCredit(highestBidder.getCredit() + highestBid.getAmount());
                    userService.update(highestBidder);
                    System.out.println("reimbursing user " + highestBidder.getUsername());
                }
                var erasedUser = userService.getUserbyUsername("erasedUser");
                item.setUser(erasedUser);
            }
        });

        userService.removeUser(userToDelete.getUserID());
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}
