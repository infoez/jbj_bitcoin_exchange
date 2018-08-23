package com.transaction.model;

public class OrderVO {

    private String orderId;
    private String tranType;
    private String price;
    private String originalQuantity;
    private String quantityLeft;
    private String email;
    private String datetime;
    private String endTranYn;
    private String endTranType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(String originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public String getQuantityLeft() {
        return quantityLeft;
    }

    public void setQuantityLeft(String quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getEndTranYn() {
        return endTranYn;
    }

    public void setEndTranYn(String endTranYn) {
        this.endTranYn = endTranYn;
    }

    public String getEndTranType() {
        return endTranType;
    }

    public void setEndTranType(String endTranType) {
        this.endTranType = endTranType;
    }
}
