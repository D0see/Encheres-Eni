package com.enchereseni.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Date;

public class Auction {

    @NotNull (message = "{NotNull.auction.date}")
    @NotBlank (message = "{NotBlank.auction.date}")
    private Date date;

    @NotNull (message = "{NotNull.auction.amount}")
    @NotBlank (message = "{NotBlank.auction.amount}")
    private int amount;

    @NotNull (message = "{NotNull.auction.user}")
    @NotBlank (message = "{NotBlank.auction.user}")
    private User user;

    @NotNull (message = "{NotNull.auction.itemSold}")
    @NotBlank (message = "{NotBlank.auction.itemSold}")
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
