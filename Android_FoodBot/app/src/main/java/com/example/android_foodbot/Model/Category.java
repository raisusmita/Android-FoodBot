package com.example.android_foodbot.Model;

public class Category {
    private  String name1;
    private  String image1;
    private  String price1;
    private  String name2;
    private  String image2;
    private  String price2;

    public Category(String name1, String image1, String price1, String name2, String image2, String price2) {
        this.name1 = name1;
        this.image1 = image1;
        this.price1 = price1;
        this.name2 = name2;
        this.image2 = image2;
        this.price2 = price2;
    }

    public Category() {
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }
}
