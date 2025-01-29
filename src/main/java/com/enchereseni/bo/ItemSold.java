package com.enchereseni.bo;

import java.util.Date;
import java.util.List;

public class ItemSold {
    private int itemId;
    private String name;
    private String description;
    private Date beginningAuctionDate;
    private Date endingAuctionDate;
    private int price;
    private boolean soldState;
    private Category category;
    private User user;
    private List<Auction> auctions;



    public ItemSold(int itemId, String name, String description, Date beginningAuctionDate, Date endingAuctionDate, int price, boolean soldState, List<Auction> auctions, Category category, User user) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.beginningAuctionDate = beginningAuctionDate;
        this.endingAuctionDate = endingAuctionDate;
        this.price = price;
        this.soldState = soldState;
        this.user = user;
        this.category = category;
        this.auctions = auctions;
    }

    public ItemSold() {}

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBeginningAuctionDate() {
        return beginningAuctionDate;
    }

    public void setBeginningAuctionDate(Date beginningAuctionDate) {
        this.beginningAuctionDate = beginningAuctionDate;
    }

    public Date getEndingAuctionDate() {
        return endingAuctionDate;
    }

    public void setEndingAuctionDate(Date endingAuctionDate) {
        this.endingAuctionDate = endingAuctionDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSoldState() {
        return soldState;
    }

    public void setSoldState(boolean soldState) {
        this.soldState = soldState;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
