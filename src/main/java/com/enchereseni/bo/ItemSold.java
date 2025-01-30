package com.enchereseni.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

import java.time.LocalDate;
import java.util.List;

public class ItemSold {

    @NotNull
    private int itemId;

    @NotNull (message = "{NotNull.itemSold.name}")
    @NotBlank (message = "{NotBlank.itemSold.name}")
    @Size(min=2, max=30, message="{WrongSize.itemSold.name}")
    private String name;

    @NotNull (message = "{NotNull.itemSold.description}")
    @NotBlank (message = "{NotBlank.itemSold.description}")
    @Size(min=10, max=300, message="{WrongSize.itemSold.description}")
    private String description;

    @NotNull (message = "{NotNull.itemSold.beginningAuctionDate}")
    private Date beginningAuctionDate;

    @NotNull (message = "{NotNull.itemSold.endingAuctionDate}")
    private Date endingAuctionDate;

    private int price;

    private LocalDate beginningAuctionDate;
    private LocalDate endingAuctionDate;
    private int firstPrice;
    private int finalPrice;
    private boolean soldState;

    @NotNull (message = "{NotNull.itemSold.category}")
    private Category category;

    @NotNull (message = "{NotNull.itemSold.user}")
    private User user;
    private List<Auction> auctions;



    public ItemSold(int itemId, String name, String description, LocalDate beginningAuctionDate, LocalDate endingAuctionDate, int firstPrice,int finalPrice, boolean soldState, List<Auction> auctions, Category category, User user) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.beginningAuctionDate = beginningAuctionDate;
        this.endingAuctionDate = endingAuctionDate;
        this.firstPrice = firstPrice;
        this.finalPrice = finalPrice;
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

    public LocalDate getBeginningAuctionDate() {
        return beginningAuctionDate;
    }

    public void setBeginningAuctionDate(LocalDate beginningAuctionDate) {
        this.beginningAuctionDate = beginningAuctionDate;
    }

    public LocalDate getEndingAuctionDate() {
        return endingAuctionDate;
    }

    public void setEndingAuctionDate(LocalDate endingAuctionDate) {
        this.endingAuctionDate = endingAuctionDate;
    }

    public int getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(int firstPrice) {
        this.firstPrice = firstPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
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
