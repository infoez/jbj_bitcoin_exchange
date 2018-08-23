package com.core;


import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

public class Row {

    private String price;
    private Queue<Order> queue;

    public Row(String price) {
        this.price = price;
        this.queue = new LinkedList<>();
    }

    Order peek() {
        return queue.peek();
    }

    Order poll() {
        return queue.poll();
    }

    public void add(Order c) {
        queue.add(c);
    }

    void remove() {
        queue.remove();
    }

    void remove(Predicate filter){
        queue.removeIf(filter);
    }

    int size(){
        return queue.size();
    }

    public String getPrice() {
        return price;
    }

    public String getQuantityLeft() {
        BigDecimal result = new BigDecimal("0");
        for(Order o : queue){
            result = result.add(new BigDecimal(o.getQuantity()));
        }
        return result.toString();
    }

    Queue<Order> getQueue(){
        return this.queue;
    }

    public boolean isQueueContained(){
        return this.queue.size() > 0;
    }

}
