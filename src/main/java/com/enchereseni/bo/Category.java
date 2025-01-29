package com.enchereseni.bo;

public class Category {
    private int category;
    private String wording;

    public Category() {
    }

    public Category(int category, String wording) {
        this.category = category;
        this.wording = wording;
    }


    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }


}
