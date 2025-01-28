package com.enchereseni.dal;

import com.enchereseni.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    private final String FIND_BY_ID = """
    SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur
    FROM UTILISATEURS
    WHERE no_utilisateur = :no_utilisateur
""";

    private final String FIND_ALL = """
    SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur
    FROM UTILISATEURS
""";

    private final String UPDATE = """
    UPDATE UTILISATEURS
    SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone,
        rue = :rue, code_postal = :code_postal, ville = :ville, mot_de_passe = :mot_de_passe,
        credit = :credit, administrateur = :administrateur
    WHERE no_utilisateur = :no_utilisateur
""";

    private final String DELETE = """
    DELETE FROM UTILISATEURS
    WHERE no_utilisateur = :no_utilisateur
""";

    private final String DELETE_NO_CREDIT = """
    DELETE FROM UTILISATEURS
    WHERE credit = 0
""";

    private final String INSERT = """
    INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
    VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur)
""";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<User> read() {
        return jdbcTemplate.query(FIND_ALL, new UserDAOImpl.UserRowMapper());
    }

    @Override
    public User read(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new UserDAOImpl.UserRowMapper());
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void delete(int id) {

    }

    public static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserID(rs.getInt("no_utilisateur"));
            user.setPseudo(rs.getString("pseudo"));
            user.setFirstName(rs.getString("nom"));
            user.setLastName(rs.getString("prenom"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getString("telephone"));
            user.setAddress(rs.getString("rue"));
            user.setZipCode(rs.getString("code_postal"));
            user.setCity(rs.getString("ville"));
            user.setPassword(rs.getString("mot_de_passe"));
            user.setCredit(rs.getInt("credit"));
            user.setAdmin(rs.getBoolean("administrateur"));
            return user;
        }
    }


}
