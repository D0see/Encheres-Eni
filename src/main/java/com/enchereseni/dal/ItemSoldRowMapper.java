package com.enchereseni.dal;

import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemSoldRowMapper implements RowMapper<ItemSold> {
    
    UserService userService;
    Category category = new Category();

    @Override
    public ItemSold mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemSold item = new ItemSold();
        item.setItemId(rs.getInt("no_article"));
        item.setName(rs.getString("nom_article"));
        item.setDescription(rs.getString("DESCRIPTION"));
        item.setBeginningAuctionDate(rs.getDate("date_debut_encheres").toLocalDate());
        item.setEndingAuctionDate(rs.getDate("date_fin_encheres").toLocalDate());
        item.setFirstPrice(rs.getInt("prix_initial"));
        item.setFinalPrice(rs.getInt("prix_vente"));


        Category category = new Category();
        category.setCategory(rs.getInt("no_categorie"));
        category.setWording(rs.getString("libelle"));
        item.setCategory(category);

        int userId = rs.getInt("no_utilisateur");
        User user = userService.getUserbyID(userId);
        item.setUser(user);



//cambiar manera de setear el user

        // Aquí puedes agregar lógica adicional para cargar las sublistas (como auctions),
        // dependiendo de cómo estructures las relaciones en la base de datos

        return item;
    }
}

