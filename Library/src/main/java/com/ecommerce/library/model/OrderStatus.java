package com.ecommerce.library.model;

public enum OrderStatus {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    PENDING("Pending"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
