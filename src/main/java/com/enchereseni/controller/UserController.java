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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

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
        targetUser.ifPresent(user -> {user.setCredit(userService.getUserbyUsername(principal.getName()).getCredit());
            model.addAttribute("user", user);
        });
        return "affichageUser";
    }

    @PostMapping("/user")
    public String modifyUser(User user, Principal principal, HttpServletRequest request) {
        userService.getUsers().stream().filter(user1 -> user1.getUsername().equals(user.getUsername())).findFirst().ifPresent(user1 -> {
            user.setUserID(user1.getUserID());
        });
        var currName = principal.getName();
        var newName = user.getUsername();
        var currEmail = userService.getUserbyUsername(principal.getName()).getEmail();
        var newEmail = user.getEmail();


        System.out.println(currName + " currName");
        System.out.println(newName + " newName");
        System.out.println(currEmail + " currEmail");
        System.out.println(newEmail + " newEmail");
        if ((!currName.equals(newName) && userService.getUsers().stream().anyMatch(user1 -> user1.getUsername().equals(newName))) ||
                (!currEmail.equals(newEmail) && userService.getUsers().stream().anyMatch(user1 -> user1.getEmail().equals(newEmail)))) {
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
        AtomicBoolean canDelete = new AtomicBoolean(true);
        System.out.println("delete my account");
        User userToDelete = userService.getUserbyUsername(principal.getName());

//        auctionService.getAuctionsByUsername(userToDelete.getUsername()).forEach(auction -> {
//            auction.setUser(userService.getUserbyUsername("erasedUser"));
//        });

        itemService.getItems().forEach(item -> {
            if (item.getBeginningAuctionDate().isBefore(LocalDate.now()) &&
                        item.getEndingAuctionDate().isAfter(LocalDate.now()) && item.getUser().getUsername().equals(userToDelete.getUsername())) {

                System.out.println("passes through");
                canDelete.set(false);
            }
        });

        if (canDelete.get()) {
            auctionService.getAllAuctions().forEach(auction -> {
                if (auction.getUser().getUsername().equals(userToDelete.getUsername())) {
                    System.out.println("deleting auction" + auction.getItemSold().getName() + " " + auction.getUser().getUsername());
                    auctionService.deleteAuctionByUserId(userToDelete.getUserID());
                }
            });

            System.out.println("delete");
            userService.removeUser(userToDelete.getUserID());
            request.getSession().invalidate();
            SecurityContextHolder.clearContext();
        }
        return "redirect:/";
    }
}
