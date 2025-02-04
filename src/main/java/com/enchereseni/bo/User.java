package com.enchereseni.bo;

import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    @NotNull
    private int userID;

    @NotNull (message = "{NotNull.user.pseudo}")
    @Max(value=30, message="{WrongSize.user.pseudo}")
    @NotBlank (message = "{NotBlank.user.pseudo}")
    private String pseudo;

    @NotNull (message = "{NotNull.user.firstName}")
    @Max(value=30, message="{WrongSize.user.firstName}")
    @NotBlank (message = "{NotBlank.user.firstName}")
    private String firstName;

    @NotNull (message = "{NotNull.user.lastName}")
    @Max(value=30, message="{WrongSize.user.lastName}")
    @NotBlank (message = "{NotBlank.user.lastName}")
    private String lastName;

    @Email (message = "{Email.user.email}")
    @NotBlank (message = "{NotBlank.user.email}")
    private String email;

    @NotNull (message = "{NotNull.user.phone}")
    @Max(value=15, message="{WrongSize.user.phone}")
    @NotBlank (message = "{NotBlank.user.phone}")
    private String phone;

    @NotNull (message = "{NotNull.user.address}")
    @Max(value=30, message="{WrongSize.user.address}")
    @NotBlank (message = "{NotBlank.user.address}")
    private String address;

    @NotNull (message = "{NotNull.user.zipCode}")
    @Max(value=10, message="{WrongSize.user.zipCode}")
    @NotBlank (message = "{NotBlank.user.zipCode}")
    private String zipCode;

    @NotNull (message = "{NotNull.user.city}")
    @Max(value=30, message="{WrongSize.user.city}")
    @NotBlank (message = "{NotBlank.user.city}")
    private String city;

    @NotNull (message = "{NotNull.user.password}")
    @NotBlank (message = "{NotBlank.user.password}")
    private String password;

    @NotNull
    private long credit;

    @NotNull
    private boolean admin;
    //Relations

    public User() {
    }

    public User(int userID, String pseudo, String firstName, String lastName, String email, String phone, String address, String zipCode, String city, String password, long credit, boolean admin) {
        this.userID = userID;
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.password = password;
        this.credit = credit;
        this.admin = admin;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return pseudo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
    public int getUserID() {return userID;}

    public void setUserID(int userID) {this.userID = userID;}
}


