package com.enchereseni.controller;

import com.enchereseni.bo.ItemSold;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ObjectCreationController {

    @GetMapping("/creerObjet")
    public String creerObjet(Model model) {
        model.addAttribute("objet", new ItemSold());
        return "objectCreationForm";
    }

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
