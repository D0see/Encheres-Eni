package com.enchereseni.bo;

import java.util.Date;

public class ItemSold {
    private int itemId;
    private String itemName;
    private String itemDescription;
    private Date beginningAuctionDate;
    private Date endingAuctionDate;
    private int itemPrice;
    private int itemSold;
    private String itemQuality;
    //Relations
    private User user;
}
