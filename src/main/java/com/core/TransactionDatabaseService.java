package com.core;

import com.exceptions.ServiceException;

import java.util.List;

public interface TransactionDatabaseService {

    void sync(List bids, List asks) throws ServiceException;

    void trade(TradeVO vo) throws ServiceException;

    void bid(OrderVO vo) throws ServiceException;

    void ask(OrderVO vo) throws ServiceException;

    OrderVO cancelBid(OrderVO vo) throws ServiceException;

    OrderVO cancelAsk(OrderVO vo) throws ServiceException;
}
