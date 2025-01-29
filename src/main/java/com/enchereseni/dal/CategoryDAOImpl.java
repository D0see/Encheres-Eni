package com.enchereseni.dal;

import com.enchereseni.bo.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO {


    private JdbcTemplate jdbcTemplate;

    // Constructor para inyectar el JdbcTemplate
    public void CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT no_categorie, libelle FROM categories"; // Consulta SQL

        // Usamos RowMapper para mapear cada fila a un objeto Category
        RowMapper<Category> rowMapper = (rs, rowNum) -> new Category(
                rs.getInt("no_categorie"),
                rs.getString("libelle")
        );

        // Ejecutamos la consulta y obtenemos la lista de categorías
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Category getCategoryById(int noCategorie) {
        String sql = "SELECT no_categorie, libelle FROM categorie WHERE no_categorie = ?"; // Consulta SQL

        // Usamos RowMapper para mapear la fila a un objeto Category
        RowMapper<Category> rowMapper = (rs, rowNum) -> new Category(
                rs.getInt("no_categorie"),
                rs.getString("libelle")
        );

        // Ejecutamos la consulta con el parámetro no_categorie y obtenemos una categoría
        return jdbcTemplate.queryForObject(sql, rowMapper, noCategorie);

    }
}
