package com.enchereseni;

import com.enchereseni.bo.Auction;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import com.enchereseni.dal.ItemSoldDAO;
import com.enchereseni.dal.UserDAO;
import com.enchereseni.dal.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EncheresEniApplication {
    @Autowired
    private ItemSoldDAO itemSoldDAO;
    public static void main(String[] args) {
        SpringApplication.run(EncheresEniApplication.class, args);
    }




}


//
//    @EventListener(ApplicationReadyEvent.class)
//    public void testCreateItemSold() {
//
//        Category testCategory = new Category();
//        testCategory.setCategory(1);  // ID o valores que correspondan
//        testCategory.setWording("Categoría de prueba");  // Nombre de la categoría
//
//
//        List<Auction> auctions = new ArrayList<>();


//        User testUser = new User();
//        testUser.setUserID(1); // o el ID que necesites para el test
//        testUser.setPseudo("TestUser");
//        testUser.setFirstName("Nombre");
//        testUser.setLastName("Apellido");
//        testUser.setEmail("testuser@example.com");
//        testUser.setPhone("123456789");
//        testUser.setAddress("Calle Ficticia");
//        testUser.setZipCode("12345");
//        testUser.setCity("Ciudad");
//        testUser.setPassword("password");
//        testUser.setCredit(1000); // o el valor que necesites
//        testUser.setAdmin(false); // o el valor que necesites
//        testUser.setAuctions(auctions);







////         Crear un objeto ItemSold
//        ItemSold newItem = new ItemSold();
//        newItem.setName("Laptop");
//        newItem.setDescription("Laptop de última generación");
//        newItem.setBeginningAuctionDate(new Date());
//        newItem.setEndingAuctionDate(new Date(System.currentTimeMillis() + 86400000)); // +1 día
//        newItem.setPrice(1000);
//        newItem.setUs;
//        newItem.setSoldState(false);
//        newItem.setCategory(testCategory);
//
//        // Crear la primera subasta
//        Auction auction1 = new Auction();
//        auction1.setDate(new Date());
//        auction1.setAmount(600); // Precio actual de la subasta
//        auction1.setUser(testUser); // Asociar la subasta al usuario
//        auction1.setItemSold(newItem); // Asociar el artículo vendido a la subasta
//
//
//        auctions.add(auction1);
//        testUser.setAuctions(auctions);
//
//
//
////         Crear el ítem en la base de datos
//        itemSoldDAO.createItemSold(newItem);
//
////         Verificar si se guardó correctamente
//        ItemSold retrievedItem = itemSoldDAO.getItemSoldById(newItem.getItemId());
//        if (retrievedItem != null) {
//            System.out.println("¡El ítem se creó correctamente en la base de datos!");
//            System.out.println("Nombre: " + retrievedItem.getName());
//        } else {
//            System.out.println("Error al guardar el ítem.");
//        }
//    }



