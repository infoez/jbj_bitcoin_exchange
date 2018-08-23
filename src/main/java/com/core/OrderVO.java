package com.core;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class OrderVO {

    private String orderId;
    private String tranType;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String price;
    @NotEmpty
    @NotNull
    private String quantity;
    private String dateTime;
    private String endTranYN;
    @NotEmpty
    @NotNull
    private String token;
    private boolean isEnough;
    @NotNull
    @NotEmpty
    private String coinId;
    private List<TradeVO> transactionHistory = new ArrayList<>();

    /*****************************************************************************************************************************************************************************************************************/

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEndTranYN() {
        return endTranYN;
    }

    public void setEndTranYN(String endTranYN) {
        this.endTranYN = endTranYN;
    }

    public boolean isEnough() {
        return isEnough;
    }

    public void setEnough(boolean enough) {
        isEnough = enough;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public List<TradeVO> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransactionHistory(TradeVO tradeVO) {
        this.transactionHistory.add(tradeVO);
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "tranType='" + tranType + '\'' +
                ", email='" + email + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
