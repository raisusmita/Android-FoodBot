package com.example.android_foodbot_admin.Model;

import java.util.List;

public class Request{
    private String phone;
    private String name;
    private String total;
    private String status;
    private List<Order> foods;  //List of food order

    public Request() {    }

    public Request(String phone, String name, String total, List<Order> foods) {
        this.phone = phone;
        this.name = name;
        this.total = total;
        this.status = "0";
        this.foods = foods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
