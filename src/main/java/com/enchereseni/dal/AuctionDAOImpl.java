package com.enchereseni.dal;

import com.enchereseni.bll.AuctionService;
import com.enchereseni.bll.ItemService;
import com.enchereseni.bll.UserService;
import com.enchereseni.bll.UserServiceImpl;
import com.enchereseni.bo.Auction;
import com.enchereseni.bo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Repository
public class AuctionDAOImpl implements AuctionDAO {

    private final String INSERT = """
    INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)
    VALUES (?, ?, ?, ?)
""";

    static final String UPDATE = """
    UPDATE ENCHERES
    SET
        date_enchere = ?,
        montant_enchere = ?
    WHERE no_utilisateur = ? AND no_article = ?
""";

    private final UserService userService;
    private final ItemService itemService;
    JdbcTemplate jdbcTemplate;


    public AuctionDAOImpl(JdbcTemplate jdbcTemplate, UserService userService, ItemService itemService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
        this.itemService = itemService;
    }

    @Override
    public void createAuction(Auction auction) {
        jdbcTemplate.update(INSERT,
                auction.getUser().getUserID(),
                auction.getItemSold().getItemId(),
                auction.getDate(),
                auction.getAmount()
        );
    }

    @Override
    public void updateAuction(Auction auction) {
        jdbcTemplate.update(UPDATE,
                auction.getDate(),
                auction.getAmount(),
                auction.getUser().getUserID(),
                auction.getItemSold().getItemId()
        );
    }

    @Override
    public void deleteAuction(Auction auction) {

    }

    @Override
    public List<Auction> getAuctions() {
        String FIND_ALL = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
        return jdbcTemplate.query(FIND_ALL, new AuctionRowMapper(itemService, userService));
    }
}
