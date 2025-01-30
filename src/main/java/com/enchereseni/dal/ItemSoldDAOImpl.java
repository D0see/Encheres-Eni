package com.enchereseni.dal;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import com.enchereseni.bll.UserService;
import com.enchereseni.bo.Category;
import com.enchereseni.bo.ItemSold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;



    @Repository
    public class ItemSoldDAOImpl implements ItemSoldDAO {

        private final UserService userService;



        private final String FIND_BY_ID = """
     SELECT av.no_article, av.nom_article, av.description, av.date_debut_encheres, av.date_fin_encheres, 
            av.prix_initial, av.prix_vente, 
            c.no_categorie, c.libelle, 
            u.no_utilisateur
     FROM ARTICLES_VENDUS av
     JOIN CATEGORIES c ON av.no_categorie = c.no_categorie
     JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur
     WHERE av.no_article = :id
""";

        private final String FIND_ALL = """
     SELECT av.no_article, av.nom_article, av.description, av.date_debut_encheres, av.date_fin_encheres, 
            av.prix_initial, av.prix_vente, 
            c.no_categorie, c.libelle, 
            u.no_utilisateur
     FROM ARTICLES_VENDUS av
     JOIN CATEGORIES c ON av.no_categorie = c.no_categorie
     JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur
""";
        private final String UPDATE = """
                     UPDATE ARTICLES_VENDUS SET NAME = :name, DESCRIPTION = :description, BEGINNING_AUCTION_DATE = :beginningAuctionDate,
                     ENDING_AUCTION_DATE = :endingAuctionDate, PRICE = :price, SOLD_STATE = :soldState, CATEGORY_ID = :categoryId
                     WHERE ID = :id
                """;

        private final String DELETE = "DELETE FROM ITEM_SOLD WHERE ID = :id";

        @Autowired
        private NamedParameterJdbcTemplate jdbcTemplate;


        private final String INSERT = """
    INSERT INTO ARTICLES_VENDUS 
        (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, no_utilisateur, no_categorie) 
    VALUES 
        (:nomArticle, :description, :beginningAuctionDate, :endingAuctionDate, :initialPrice,:finalPrice, :userId, :categoryId)
""";

        public ItemSoldDAOImpl(UserService userService) {
            this.userService = userService;
        }

        @Override
        public void createItemSold(ItemSold itemSold) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("nomArticle", itemSold.getName());
            namedParameters.addValue("description", itemSold.getDescription());
            namedParameters.addValue("beginningAuctionDate", itemSold.getBeginningAuctionDate());
            namedParameters.addValue("endingAuctionDate", itemSold.getEndingAuctionDate());
            namedParameters.addValue("initialPrice", itemSold.getFirstPrice());
            namedParameters.addValue("finalPrice", itemSold.getFinalPrice());
            namedParameters.addValue("userId", itemSold.getUser().getUserID()); // Asume que existe un User
            namedParameters.addValue("categoryId", itemSold.getCategory().getCategory()); // Usar getNoCategorie()
            jdbcTemplate.update(INSERT, namedParameters, keyHolder);
        }


        @Override
        public ItemSold getItemSoldById(int id) {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("id", id);
            return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new ItemSoldRowMapper(userService));
        }

        @Override
        public List<ItemSold> getAllItemSold() {
            return jdbcTemplate.query(FIND_ALL, new ItemSoldRowMapper(userService));
        }

    }







