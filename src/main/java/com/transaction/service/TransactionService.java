package com.transaction.service;

import com.core.OrderVO;

import java.util.Map;

public interface TransactionService {

    public Map getOrders();

    void bid(OrderVO vo) throws Exception;

    public void ask(OrderVO vo) throws Exception;


}
