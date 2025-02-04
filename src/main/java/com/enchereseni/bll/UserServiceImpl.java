package com.enchereseni.bll;

import com.enchereseni.bo.User;
import com.enchereseni.dal.UserDAO;
import com.enchereseni.dal.UserDAOImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.enchereseni.dal.exceptions.BusinessCode;
import com.enchereseni.dal.exceptions.BusinessException;



import java.util.List;
@Service
public class UserServiceImpl implements UserService {

   private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(User user) {
    userDAO.create(user);
    }

    @Override
    public void removeUser(int id) {
        userDAO.delete(id);

    }

    @Override
    public List<User> getUsers() {
        System.out.println(userDAO.readAll()+ "UserServiceImpl");
        return userDAO.readAll();

    }

    @Override
    public User getUserbyID(int id) {
        return userDAO.read(id);
    }

    @Override
    public User getUserbyUsername(String username) {
        return userDAO.readAll().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public void update(User user) {
userDAO.update(user);
    }

    @Override
    public boolean isUnique(User user) {
        if(userDAO.readAll().stream().anyMatch(user1 -> user.getPseudo().equals(user1.getPseudo()) || user1.getEmail().equals(user.getEmail()))) {
            return false;
        };
        return true;
    }

    @Override
    public void creerUser(User user) {

    }

    @Transactional
    public void creationUser(User user) {
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validerUserId(user, be);
        isValid &= validerUserPseudo(user.getPseudo(), be);
        isValid &= validerUserFirstName(user.getFirstName(), be);
        isValid &= validerUserLastName(user.getLastName(), be);
        isValid &= validerUserEmail(user.getEmail(), be);
        isValid &= validerUserPhone(user.getPhone(), be);
        isValid &= validerUserAddress(user.getAddress(), be);
        isValid &= validerUserZipCode(user.getZipCode(), be);
        isValid &= validerUserCity(user.getCity(), be);
        isValid &= validerUserPassword(user.getPassword(), be);


        if (isValid) {
            userDAO.create(user);
        } else {
            throw be;
        }
    }
        private boolean validerUserId(User u, BusinessException be){
            if(u.getPseudo().equals(u.getEmail())) {
                be.add(BusinessCode.VALIDATION_USER_ID);
                return false;
            }
            return true;
        }

        //PSEUDO
        private boolean validerUserPseudo(String pseudo, BusinessException be){
        if(pseudo == null || pseudo.isEmpty()) {
            be.add(BusinessCode.VALIDATION_USER_PSEUDO_EMPTY);
            return false;
        }
        if (pseudo.length()>30){
            be.add(BusinessCode.VALIDATION_USER_PSEUDO_LENGHT);
            return false;
        }
        if (pseudo.isBlank()){
            be.add(BusinessCode.VALIDATION_USER_BLANK);
            return false;
        }
        if (userDAO.readAll().stream().anyMatch(user -> user.getPseudo().equals(pseudo))) {
            be.add(BusinessCode.VALIDATION_USER_NOTUNIQUE);
        }
        if(!pseudo.matches("^[a-zA-Z0-9]+$")){
            be.add(BusinessCode.VALIDATION_USER_PSEUDO_REGEX);
            return false;
        }
        return true;
        }

        //FIRSTNAME
        private boolean validerUserFirstName(String firstName, BusinessException be){
            if(firstName == null || firstName.isEmpty()) {
                be.add(BusinessCode.VALIDATION_USER_FIRSTNAME_EMPTY);
                return false;
            }
            if (firstName.isBlank()){
                be.add(BusinessCode.VALIDATION_USER_FIRSTNAME_BLANK);
                return false;
        }
            if (firstName.length()>30){
                be.add(BusinessCode.VALIDATION_USER_FIRSTNAME_LENGHT);
                return false;
            }
            return true;
        }

        //LASTNAME
        private boolean validerUserLastName(String lastName, BusinessException be){
            if(lastName == null || lastName.isEmpty()) {
                be.add(BusinessCode.VALIDATION_USER_LASTNAME_EMPTY);
                return false;
            }
            if (lastName.isBlank()){
                be.add(BusinessCode.VALIDATION_USER_LASTNAME_BLANK);
                return false;
            }
            if (lastName.length()>30){
                be.add(BusinessCode.VALIDATION_USER_LASTNAME_LENGHT);
                return false;
            }
            return true;
        }

        //EMAIL
        private boolean validerUserEmail(String email, BusinessException be){
        if(email == null || email.isEmpty()) {
            be.add(BusinessCode.VALIDATION_USER_EMAIL_EMPTY);
            return false;
        }
        if (email.isBlank()){
            be.add(BusinessCode.VALIDATION_USER_EMAIL_BLANK);
            return false;
            }
        if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            be.add(BusinessCode.VALIDATION_USER_EMAIL_REGEX);
            return false;
            }
        return true;
        }

        //PHONE
        private boolean validerUserPhone(String phone, BusinessException be){
        if(phone == null || phone.isEmpty()) {
            be.add(BusinessCode.VALIDATION_USER_PHONE_EMPTY);
            return false;
        }
        if (phone.isBlank()){
            be.add(BusinessCode.VALIDATION_USER_PHONE_BLANK);
            return false;
        }
        if(!phone.matches("^\\+?(\\d{1,2}[\\s.-]?)?(\\(?\\d{1,4}\\)?[\\s.-]?)?(\\d{1,4}[\\s.-]?)?\\d{2,4}[\\s.-]?\\d{2,4}$")){
            be.add(BusinessCode.VALIDATION_USER_PHONE_REGEX);
            return false;
            }
        return true;
        }

        //ADDRESS
        private boolean validerUserAddress(String address, BusinessException be){
        if(address == null || address.isEmpty()) {
            be.add(BusinessCode.VALIDATION_USER_ADDRESS_EMPTY);
            return false;
        }
        if (address.isBlank()){
            be.add(BusinessCode.VALIDATION_USER_ADDRESS_BLANK);
            return false;
        }
        if (address.length()>30){
            be.add(BusinessCode.VALIDATION_USER_ADDRESS_LENGHT);
            return false;
        }
        return true;
        }

        //ZIPCODE
        private boolean validerUserZipCode(String zipCode, BusinessException be){
        if(zipCode == null || zipCode.isEmpty()) {
            be.add(BusinessCode.VALIDATION_USER_ZIPCODE_EMPTY);
            return false;
        }
        if (zipCode.length()>10){
            be.add(BusinessCode.VALIDATION_USER_ZIPCODE_LENGHT);
            return false;
        }
        if(!zipCode.matches("^\\d{5}$")){
            be.add(BusinessCode.VALIDATION_USER_ZIPCODE_REGEX);
            return false;
        }
        return true;
        }

        //CITY
        private boolean validerUserCity(String city, BusinessException be){
        if(city == null || city.isEmpty()) {
            be.add(BusinessCode.VALIDATION_USER_CITY_EMPTY);
            return false;
        }
        if (city.length()>30){
            be.add(BusinessCode.VALIDATION_USER_CITY_LENGHT);
            return false;
        }
        if (city.isBlank()){
            be.add(BusinessCode.VALIDATION_USER_CITY_BLANK);
            return false;
        }
        if (!city.matches("^[A-Za-zÀ-ÿa-zA-Z\\s'-]+$")){
            be.add(BusinessCode.VALIDATION_USER_CITY_REGEX);
            return false;
        }
        return true;
        }

        //PASSWORD
        private boolean validerUserPassword(String password, BusinessException be){
        if(password == null || password.isEmpty()) {
            be.add(BusinessCode.VALIDATION_USER_PASSWORD_EMPTY);
            return false;
        }
        if (password.length()>255){
            be.add(BusinessCode.VALIDATION_USER_PASSWORD_LENGHT);
            return false;
        }
        if (password.isBlank()){
            be.add(BusinessCode.VALIDATION_USER_PASSWORD_BLANK);
            return false;
        }
        return true;
        }
}


