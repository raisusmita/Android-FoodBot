package com.example.android_foodbot_admin.Model;

public class Category {
    private  String name1;
    private  String image1;

    public Category() {
    }

    public Category(String nmae1, String image1) {
        this.name1 = nmae1;
        this.image1 = image1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String nmae1) {
        this.name1 = nmae1;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }
}
