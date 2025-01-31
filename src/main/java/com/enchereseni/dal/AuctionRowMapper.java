package com.enchereseni.dal;

import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Auction;
import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class AuctionRowMapper implements RowMapper<Auction> {

    ItemService itemService;
    UserService userService;

    public AuctionRowMapper(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @Override
    public Auction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auction auction = new Auction();

        auction.setDate(rs.getDate("date_enchere"));
        auction.setAmount(rs.getInt("montant_enchere"));

        auction.setItemSold(itemService.getItemById(rs.getInt("no_article")));
        auction.setUser(userService.getUserbyID(rs.getInt("no_utilisateur")));

        return auction;
    }
}
