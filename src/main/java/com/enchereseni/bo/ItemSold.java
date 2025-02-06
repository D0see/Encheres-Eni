package com.enchereseni.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import java.time.LocalDate;
import java.util.List;

public class ItemSold {

    @NotNull
    private int itemId;

    @NotBlank (message = "{NotBlank.itemSold.name}")
    @Size(min=2, max=30, message="{WrongSize.itemSold.name}")
    private String name;

    @NotBlank (message = "{NotBlank.itemSold.description}")
    @Size(min=10, max=300, message="{WrongSize.itemSold.description}")
    private String description;

    @NotNull (message = "{NotNull.itemSold.beginningAuctionDate}")
    private LocalDate beginningAuctionDate;

    @NotNull (message = "{NotNull.itemSold.endingAuctionDate}")
    private LocalDate endingAuctionDate;

    private int firstPrice;
    private int finalPrice;
    private boolean soldState;

    @NotNull (message = "{NotNull.itemSold.category}")
    private Category category;

    private User user;
    private String imagePath;
    private transient MultipartFile imageFile;

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    private List<Auction> auctions;

    private EtatVente etatVente;
    private Auction highestBid;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ItemSold(int itemId, String name, String description, LocalDate beginningAuctionDate, LocalDate endingAuctionDate, int firstPrice, int finalPrice, boolean soldState, List<Auction> auctions, Category category, User user) {
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
        this.imagePath = null;
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
        return  this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EtatVente getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(EtatVente etatVente) {
        this.etatVente = etatVente;
    }

    public Auction getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Auction highestBid) {
        this.highestBid = highestBid;
    }
}
