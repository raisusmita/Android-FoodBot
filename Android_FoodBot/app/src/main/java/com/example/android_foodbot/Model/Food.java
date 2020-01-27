package com.example.android_foodbot.Model;

public class Food {
    private String name;
    private String price;
    private String image;
    private  String menuid;
    private  String description;
    private  String discount;


    public Food() {
    }

    public Food(String name, String price, String image, String menuid, String description, String discount) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.menuid = menuid;
        this.description = description;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
