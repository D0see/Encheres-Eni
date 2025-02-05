package com.enchereseni.controller;

import com.enchereseni.bll.AuctionService;
import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.*;

import com.enchereseni.dal.CategoryDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.CommonDataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ObjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CommonDataSource commonDataSource;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private AuctionService auctionService;



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

    @PostMapping("/deleteItem/{param}")
    public String deleteItem(@PathVariable int param, Model model) {
        itemService.removeItem(itemService.getItemById(param));
        return "redirect:/encheres";
    }

    @PostMapping("/vendre")
    public String createItem(@Valid @ModelAttribute ItemSold itemSold, @ModelAttribute PickUp pickUp, @RequestParam("file") MultipartFile file, Principal principal, BindingResult result, Model model ) {

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "itemCreation";
        }

        User user=userService.getUserbyUsername(principal.getName());
        itemSold.setUser(user);

        int noCategorie = itemSold.getCategory().getCategory();
        Category category = categoryDAO.getCategoryById(noCategorie);
        itemSold.setCategory(category);

        String uploadDir = "src/main/resources/static/images";
        try {
            // Si el usuario sube una imagen
            if (!file.isEmpty()) {
                // Obtener el nombre original del archivo
                String fileName = file.getOriginalFilename();

                Path filePath = Paths.get(uploadDir + fileName);

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                itemSold.setImagePath("/images/" + fileName);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al guardar la imagen.");
            return "itemCreation";
        }

        itemService.createItem(itemSold);

        pickUp.setItemSold(itemSold);
        itemService.createPickUpWithItem(itemSold, pickUp);

        return "redirect:/";

    }




    @GetMapping("/articleDetail/{itemId}")
    public String articleDetail(@PathVariable("itemId") int itemId,@ModelAttribute User user, Principal principal,Model model) {

        itemService.getItems().stream().filter(item -> item.getItemId() == itemId).findFirst().ifPresent(item -> {
            model.addAttribute("user", userService.getUserbyUsername(principal.getName()));
            item.setAuctions(auctionService.getAllAuctions().stream()
                    .filter(auction -> auction.getItemSold().getItemId() == item.getItemId())
                    .toList());
            LocalDate today = LocalDate.now();
            if (item.getEndingAuctionDate().isBefore(today)) {
                item.setEtatVente(EtatVente.TERMINEE);
            } else if (item.getBeginningAuctionDate().isBefore(today) && item.getEndingAuctionDate().isAfter(today)) {
                item.setEtatVente(EtatVente.EN_COURS);
            } else {
                item.setEtatVente(EtatVente.EN_ATTENTE);
            }
            model.addAttribute("item", item);
            model.addAttribute("categories", itemService.getCategories());

        });


        return "itemDetail";
    }

    @GetMapping("/modifierVente/{id}")
    public String modifierVente(@PathVariable("id") int id,@ModelAttribute User user, Principal principal, Model model) {
        ItemSold item = itemService.getItemById(id);

        if (item == null || !item.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/"; // Redirigir si el artículo no existe o el usuario no es el dueño
        }
        model.addAttribute("user", userService.getUserbyUsername(principal.getName()));
        model.addAttribute("item", item);
        model.addAttribute("categories", itemService.getCategories());
        return "modifyItemSold";
    }

    @PostMapping("/modifierVente/{id}")
    public String enregistrerModification(@PathVariable("id") int id, @ModelAttribute ItemSold updatedItem, Principal principal) {
        ItemSold item = itemService.getItemById(id);

        if (item == null || !item.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/";
        }

        // Actualizar solo los campos permitidos
        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setCategory(updatedItem.getCategory());
        item.setFirstPrice(updatedItem.getFirstPrice());
        item.setBeginningAuctionDate(updatedItem.getBeginningAuctionDate());
        item.setEndingAuctionDate(updatedItem.getEndingAuctionDate());
        itemService.updateItem(item); // Guardamos la modificación
        return "redirect:/articleDetail/" + id; // Volvemos a la vista de detalles
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
