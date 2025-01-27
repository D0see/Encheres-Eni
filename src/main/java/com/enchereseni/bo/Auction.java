package com.enchereseni.bo;

import java.time.LocalTime;
import java.util.Date;

public class Auction {

    private Date auctionDate;
    private int auctionRising;
    //Relations
    private User user;
    private ItemSold itemSold;

    public Auction(Date auctionDate, int auctionRising, User user, ItemSold itemSold) {
        this.auctionDate = auctionDate;
        this.auctionRising = auctionRising;
        this.user = user;
        this.itemSold = itemSold;
    }

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(Date auctionDate) {
        this.auctionDate = auctionDate;
    }

    public int getAuctionRising() {
        return auctionRising;
    }

    public void setAuctionRising(int auctionRising) {
        this.auctionRising = auctionRising;
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
