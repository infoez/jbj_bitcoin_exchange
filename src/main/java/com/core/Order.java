package com.core;

public class Order {

    private String orderId;
    private String email;
    private String quantity;

    public Order(String order_id, String email, String quantity) {
        this.orderId = order_id;
        this.email = email;
        this.quantity = quantity;
    }

    String getOrderId() {
        return orderId;
    }

    String getEmail() {
        return email;
    }

    String getQuantity() {
        return quantity;
    }

    void setQuantity(String quantity) {this.quantity = quantity;}

    void transaction(String quantity) throws Exception {
        if(Calc.compare(quantity, this.quantity) == 1)  // ==> quantity > this.quantity
            throw new Exception();
        this.quantity = Calc.multiply(this.quantity, quantity); // this.quantity - quantity;
    }
}
