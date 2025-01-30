package com.enchereseni.dal;

import com.enchereseni.bo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT no_categorie, libelle FROM categories"; // Consulta SQL

        // We use RowMapper to map each row to a Category object.
        RowMapper<Category> rowMapper = (rs, rowNum) -> new Category(
                rs.getInt("no_categorie"),
                rs.getString("libelle")
        );

        // We execute the query and retrieve the list of categories.
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Category getCategoryById(int noCategorie) {
        String sql = "SELECT no_categorie, libelle FROM categories WHERE no_categorie = ?"; // Consulta SQL


        RowMapper<Category> rowMapper = (rs, rowNum) -> new Category(
                rs.getInt("no_categorie"),
                rs.getString("libelle")
        );

        //  We execute the query with the no_categorie parameter and retrieve a category.
        return jdbcTemplate.queryForObject(sql, rowMapper, noCategorie);

    }
}
