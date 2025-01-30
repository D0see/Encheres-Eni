package com.enchereseni.controller;

import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.PickUp;
import com.enchereseni.bo.User;
import com.enchereseni.dal.CategoryDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.sql.CommonDataSource;
import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.List;

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
    public String creerObjet( Principal principal,Model model) {

        String userConnected =principal.getName();
        userService.getUsers().stream().filter(user -> userConnected.equals(user.getUsername())).findFirst().ifPresent(user -> {

            model.addAttribute("itemSold", new ItemSold());
            model.addAttribute("categories", itemService.getCategories());
            model.addAttribute("user", user);
            model.addAttribute("pickUp", new PickUp());
        });

        return "itemCreation";
    }

    @PostMapping("/vendre")
    public String createItem(@Valid @ModelAttribute ItemSold itemSold, @ModelAttribute PickUp pickUp, Principal principal, BindingResult result, Model model ) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "itemCreation";
        }

        User user=userService.getUserbyUsername(principal.getName());
        itemSold.setUser(user);

        int noCategorie = itemSold.getCategory().getCategory();
        Category category = categoryDAO.getCategoryById(noCategorie);
        itemSold.setCategory(category);

        itemService.createItem(itemSold);

        pickUp.setItemSold(itemSold);
        itemService.createPickUpWithItem(itemSold, pickUp);

        return "redirect:/";

    }
    //pour validation
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model) {
            List<FieldError> errors = ex.getBindingResult().getFieldErrors();
            model.addAttribute("errors", errors);
            return "errorPage";  // Affiche une page d'erreur avec les messages
        }
    }
}
