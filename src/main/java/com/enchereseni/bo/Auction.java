package com.enchereseni.bo;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Date;

public class Auction {

    @NotNull
    private Date date;
    @NotNull
    private int amount;
    @NotNull
    private User user;
    @NotNull
    private ItemSold itemSold;

    public Auction(Date date, int amount, User user, ItemSold itemSold) {
        this.date = date;
        this.amount = amount;
        this.user = user;
        this.itemSold = itemSold;
    }

    public Auction() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ItemSold getItemSold() {
        return itemSold;
    }

    public void setItemSold(ItemSold itemSold) {
        this.itemSold = itemSold;
    }
}
