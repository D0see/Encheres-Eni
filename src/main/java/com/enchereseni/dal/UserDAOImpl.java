package com.enchereseni.dal;

import com.enchereseni.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbc;
    private final PasswordEncoder encoder;

    public UserDAOImpl(JdbcTemplate jdbc, PasswordEncoder encoder) {
        this.jdbc = jdbc;
        this.encoder = encoder;
    }

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

      public boolean verifyUniqueness(User user) {
           if(jdbc.queryForObject("SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = ?",Integer.class, user.getPseudo()) > 0 ||
                   jdbc.queryForObject("SELECT COUNT(*) FROM UTILISATEURS WHERE email = ?",Integer.class, user.getEmail()) > 0) {
               return true;    }
           return false;
       }






    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public void create(User user) {


        jdbc.update(
                "INSERT INTO UTILISATEURS (pseudo, mot_de_passe, email, administrateur, nom, prenom, telephone, rue, code_postal, ville, credit) " +
                        "VALUES (?, ?, ?, 0, ?, ?, ?, ?, ?, ?, 100)", // Default empty values
                user.getPseudo(),
                encoder.encode(user.getPassword()),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getAddress(),
                user.getZipCode(),
                user.getCity()
        );
    }



    @Override
    public List<User> read() {
        return null;
    }

    @Override
    public User read(int id) {
        return null;
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
}




