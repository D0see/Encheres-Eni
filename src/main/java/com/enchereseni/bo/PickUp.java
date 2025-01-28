package com.enchereseni.bo;

public class PickUp {
    private String address;
    private int zipCode;
    private String city;
    //Relations
    private ItemSold itemSold;

    public PickUp() {}

    public PickUp(String address, int zipCode, String city, ItemSold itemSold) {
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.itemSold = itemSold;
    }

    public ItemSold getItemSold() {
        return itemSold;
    }

    public void setItemSold(ItemSold itemSold) {
        this.itemSold = itemSold;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

