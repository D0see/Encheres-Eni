package com.enchereseni.dal;

import com.enchereseni.bo.ItemSold;
import com.enchereseni.bo.PickUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PickUpDAOImpl implements PickUpDAO {


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private ItemSoldDAO itemSoldDAO;


    private final String INSERT = """
    INSERT INTO  RETRAITS
        (no_articlE, rue,code_postal , ville) 
    VALUES 
        (:noArticle, :rue, :codePostal, :ville)
""";


    private final String FIND_BY_ID = """
    SELECT no_article, rue, code_postal, ville\s
    FROM RETRAITS\s
    WHERE no_article = ?;
    """;


    @Override
    public void createPickUp(PickUp pickUp) {

    }

    @Override
    public void createPickUpWithItem(ItemSold item, PickUp pickUp) {

        pickUp.setItemSold(item);

        createPickUp(pickUp);

        // Procedemos a guardar el PickUp en la base de datos
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noArticle", pickUp.getItemSold().getItemId());
        namedParameters.addValue("rue", pickUp.getAddress());
        namedParameters.addValue("codePostal", pickUp.getZipCode());
        namedParameters.addValue("ville", pickUp.getCity());

        jdbcTemplate.update(INSERT, namedParameters, new GeneratedKeyHolder());
    }


    @Override
    public PickUp getPickUp(int id) {

            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("id", id);

            return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new PickUpRowMapper());
        }



    }





