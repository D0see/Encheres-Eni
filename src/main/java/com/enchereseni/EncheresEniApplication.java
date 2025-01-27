package com.enchereseni;

import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import com.enchereseni.dal.ItemSoldDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Date;

@SpringBootApplication
public class EncheresEniApplication {
    @Autowired
    private ItemSoldDAO itemSoldDAO;
    public static void main(String[] args) {
        SpringApplication.run(EncheresEniApplication.class, args);
    }



    @EventListener(ApplicationReadyEvent.class)
    public void testCreateItemSold() {

        Category testCategory = new Category();
        testCategory.setCategory(1);  // ID o valores que correspondan
        testCategory.setWording("Categoría de prueba");  // Nombre de la categoría



        User testUser = new User();
        testUser.setId(1); // o el ID que necesites para el test
        testUser.setPseudo("TestUser");
        testUser.setFirstName("Nombre");
        testUser.setLastName("Apellido");
        testUser.setEmail("testuser@example.com");
        testUser.setPhone("123456789");
        testUser.setAddress("Calle Ficticia");
        testUser.setZipCode("12345");
        testUser.setCity("Ciudad");
        testUser.setPassword("password");
        testUser.setCredit(1000); // o el valor que necesites
        testUser.setAdmin(false); // o el valor que necesites




        // Crear un objeto ItemSold
        ItemSold newItem = new ItemSold();
        newItem.setName("Laptop");
        newItem.setDescription("Laptop de última generación");
        newItem.setBeginningAuctionDate(new Date());
        newItem.setEndingAuctionDate(new Date(System.currentTimeMillis() + 86400000)); // +1 día
        newItem.setPrice(1000);
        newItem.setUser(testUser);
        newItem.setSoldState(false);
        newItem.setCategory(testCategory);

        // Crear el ítem en la base de datos
        itemSoldDAO.createItemSold(newItem);

        // Verificar si se guardó correctamente
        ItemSold retrievedItem = itemSoldDAO.getItemSoldById(newItem.getItemId());
        if (retrievedItem != null) {
            System.out.println("¡El ítem se creó correctamente en la base de datos!");
            System.out.println("Nombre: " + retrievedItem.getName());
        } else {
            System.out.println("Error al guardar el ítem.");
        }
    }


}
