package com.core;

public class TradeVO {

    private String coinId;
    private String buyer; /** 구매자 **/
    private String seller; /** 판매자 **/
    private String price; /** 거래가 **/
    private String quantity; /** 코인 양**/
    private String krwAmount; /** 한국 돈 양**/
    private String email; /** 요청자 이메일 ( 키값 ) **/
    private String tranType; /** 거래 타입 ( bid, ask ) **/
    private String dateTime; /** 안써놔도 알잖아.. **/
    private String orderId;
    private boolean isEnough;

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getKrwAmount() {
        return krwAmount;
    }

    public void setKrwAmount(String krwAmount) {
        this.krwAmount = krwAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isEnough() {
        return isEnough;
    }

    public void setEnough(boolean enough) {
        this.isEnough = enough;
    }

    @Override
    public String toString() {
        return "{" +
                "coinId='" + coinId + '\'' +
                ", buyer='" + buyer + '\'' +
                ", seller='" + seller + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", krwAmount='" + krwAmount + '\'' +
                ", tranType='" + tranType + '\'' +
                '}';
    }
}
