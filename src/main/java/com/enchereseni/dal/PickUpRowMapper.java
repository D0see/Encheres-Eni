package com.enchereseni.dal;

import com.enchereseni.bll.ItemService;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.PickUp;
import com.enchereseni.bo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PickUpRowMapper implements RowMapper<PickUp> {

    ItemService itemService;

    @Override
    public PickUp mapRow(ResultSet rs, int rowNum) throws SQLException {
        PickUp pickUp = new PickUp();


        pickUp.setAddress(rs.getString("rue"));
        pickUp.setZipCode(rs.getInt("code_postal"));
        pickUp.setCity(rs.getString("ville"));

        int itemId = rs.getInt("no_article");
        ItemSold itemSold = itemService.getItemById(itemId);
        pickUp.setItemSold(itemSold);


        return pickUp;
    }
}
