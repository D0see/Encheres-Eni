package com.enchereseni.dal;

import com.enchereseni.bo.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserID(rs.getInt("no_utilisateur"));  // Maps "id" column to userID
        user.setPseudo(rs.getString("pseudo"));
        user.setFirstName(rs.getString("prenom"));  // Maps "prenom" to firstName
        user.setLastName(rs.getString("nom"));  // Maps "nom" to lastName
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("telephone"));  // Maps "telephone" to phone
        user.setAddress(rs.getString("rue"));  // Maps "rue" to address
        user.setZipCode(rs.getString("code_postal"));  // Maps "code_postal" to zipCode
        user.setCity(rs.getString("ville"));
        user.setPassword(rs.getString("mot_de_passe"));  // Maps "mot_de_passe" to password
        user.setCredit(rs.getLong("credit"));
        user.setAdmin(rs.getBoolean("administrateur"));  // Maps "administrateur" to admin
        return user;
    }
}