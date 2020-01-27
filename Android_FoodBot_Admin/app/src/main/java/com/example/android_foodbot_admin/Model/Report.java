package com.example.android_foodbot_admin.Model;

public class Report {
    private String RequestId;
    private String UserName;
    private String OrderStatus;
    private String Price;

    public Report(String requestId, String userName, String orderStatus, String price) {
        RequestId = requestId;
        UserName = userName;
        OrderStatus = orderStatus;
        Price = price;
    }

    public Report() {
    }


    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}


