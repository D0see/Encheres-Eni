package com.enchereseni.dal;

import com.enchereseni.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    static final String FIND_ALL = "select * from UTILISATEURS";
    static final String FIND_BY_ID = "select * from UTILISATEURS where id=?";
    static final String UPDATE = "UPDATE UTILISATEURS set pseudo=?, mot_de_passe=?, email=?, administrateur=?, nom=?, prenom=?, telephone=?, rue=?, code_postal=?, ville=?, credit=? where id=?";
    static final String DELETE = "DELETE FROM UTILISATEURS where id=?";


      public boolean isUnique(User user) {
           if(jdbc.queryForObject("SELECT COUNT(*) FROM UTILISATEURS WHERE pseudo = ?",Integer.class, user.getPseudo()) > 0 ||
                   jdbc.queryForObject("SELECT COUNT(*) FROM UTILISATEURS WHERE email = ?",Integer.class, user.getEmail()) > 0) {
               return true;    }
           return false;
       }



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
    public List<User> readAll() {
        return jdbc.query(FIND_ALL,BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User read(int id) {
        return jdbc.queryForObject(FIND_BY_ID, BeanPropertyRowMapper.newInstance(User.class), id);
        }

    @Override
    public void update(User user) {
        jdbc.update(UPDATE, user.getPseudo(),user.getPassword(),user.getEmail(),user.getLastName(),user.getFirstName(),
                user.getPhone(),user.getAddress(),user.getZipCode(),user.getCity());
    }

    @Override
    public void delete(User user) {
        delete(user.getUserID());
    }

    @Override
    public void delete(int id) {
    jdbc.update(DELETE, id);
    }
}




