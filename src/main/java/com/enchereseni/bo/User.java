package com.enchereseni.bo;

import java.util.List;

public class User {

    private String pseudo;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String adress;
    private String zipCode;
    private String city;
    private String password;
    private long credit;
    private boolean admin;
    //Relations
    private List<Buy> buys;
    private List<Sale> sales;



}
