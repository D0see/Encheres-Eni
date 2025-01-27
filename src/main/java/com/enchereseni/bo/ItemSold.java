package com.enchereseni.bo;

import java.util.Date;
import java.util.List;

public class ItemSold {
    private int itemId;
    private String itemName;
    private String itemDescription;
    private Date beginningAuctionDate;
    private Date endingAuctionDate;
    private int itemPrice;
    private int itemSold;
    private String itemQuality;
    private List<Auction> auctions;

    public ItemSold(int itemId, String itemName, String itemDescription, Date beginningAuctionDate, Date endingAuctionDate, int itemPrice, int itemSold, String itemQuality, List<Auction> auctions) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.beginningAuctionDate = beginningAuctionDate;
        this.endingAuctionDate = endingAuctionDate;
        this.itemPrice = itemPrice;
        this.itemSold = itemSold;
        this.itemQuality = itemQuality;
        this.auctions = auctions;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemSold() {
        return itemSold;
    }

    public void setItemSold(int itemSold) {
        this.itemSold = itemSold;
    }

    public String getItemQuality() {
        return itemQuality;
    }

    public void setItemQuality(String itemQuality) {
        this.itemQuality = itemQuality;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }
}
