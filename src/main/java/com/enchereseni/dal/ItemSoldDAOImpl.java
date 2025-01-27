package com.enchereseni.dal;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


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

        // Variables SQL


        private final String FIND_BY_ID = """
     SELECT ID, NAME, DESCRIPTION, BEGINNING_AUCTION_DATE, ENDING_AUCTION_DATE, PRICE, SOLD_STATE, CATEGORY_ID
     FROM ARTICLES_VENDUS WHERE ID = :id
""";

        private final String FIND_ALL = """
     SELECT ID, NAME, DESCRIPTION, BEGINNING_AUCTION_DATE, ENDING_AUCTION_DATE, PRICE, SOLD_STATE, CATEGORY_ID
     FROM ARTICLES_VENDUS
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
    INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)
    VALUES (:nomArticle, :description, :dateDebutEncheres, :dateFinEncheres, :prixInitial, :prixVente, :noUtilisateur, :noCategorie)
""";

        @Override
        public void createItemSold(ItemSold itemSold) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("nomArticle", itemSold.getName()); // Reemplazar con el nombre de la propiedad correcta
            namedParameters.addValue("description", itemSold.getDescription());
            namedParameters.addValue("dateDebutEncheres", itemSold.getBeginningAuctionDate());
            namedParameters.addValue("dateFinEncheres", itemSold.getEndingAuctionDate());
            namedParameters.addValue("prixInitial", itemSold.getPrice()); // Aquí es importante que se asocien las variables correctas
            namedParameters.addValue("prixVente", itemSold.getPrice()); // Si tienes un precio de venta, sino, déjalo en null o ajusta el código
            namedParameters.addValue("noUtilisateur", itemSold.getUser().getId()); // Necesitarás obtener el ID del usuario
            namedParameters.addValue("noCategorie", itemSold.getCategory().getCategory()); // Obtener el ID de la categoría

            jdbcTemplate.update(INSERT, namedParameters, keyHolder);

            if (keyHolder.getKey() != null) {
                itemSold.setItemId(keyHolder.getKey().intValue());
            }
        }






        @Override
        public ItemSold getItemSoldById(int id) {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("id", id);

            return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new ItemSoldRowMapper());
        }

        @Override
        public List<ItemSold> getAllItemSold() {
            return jdbcTemplate.query(FIND_ALL, new ItemSoldRowMapper());
        }







        // RowMapper para mapear los resultados de la base de datos a objetos ItemSold
        class ItemSoldRowMapper implements RowMapper<ItemSold> {
            @Override
            public ItemSold mapRow(ResultSet rs, int rowNum) throws SQLException {
                ItemSold item = new ItemSold();
                item.setItemId(rs.getInt("ID"));
                item.setName(rs.getString("NAME"));
                item.setDescription(rs.getString("DESCRIPTION"));
                item.setBeginningAuctionDate(rs.getDate("BEGINNING_AUCTION_DATE"));
                item.setEndingAuctionDate(rs.getDate("ENDING_AUCTION_DATE"));
                item.setPrice(rs.getInt("PRICE"));
                item.setSoldState(rs.getBoolean("SOLD_STATE"));


                Category category = new Category();
                category.setCategory(rs.getInt("CATEGORY_ID"));
                item.setCategory(category);

                // Aquí puedes agregar lógica adicional para cargar las sublistas (como auctions),
                // dependiendo de cómo estructures las relaciones en la base de datos

                return item;
            }
        }
    }


