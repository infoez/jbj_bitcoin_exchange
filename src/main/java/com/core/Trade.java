package com.core;

import com.exceptions.ServiceException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Trade implements Serializable {

    private static final long serialVersionUID = 8506241018216L;

    private List<Row> bids;
    private List<Row> asks;
    private Action bidAction;
    private Action cancelBidAction;
    private Action askAction;
    private Action cancelAskAction;
    private Action syncAction;

    public Trade(Action bidAction, Action cancelBidAction, Action askAction, Action cancelAskAction, Action syncAction) {
        this.bids = new ArrayList<>();
        this.asks = new ArrayList<>();
        this.bidAction = bidAction;
        this.cancelBidAction = cancelBidAction;
        this.askAction = askAction;
        this.cancelAskAction = cancelAskAction;
        this.syncAction = syncAction;
    }

    public List<Row> getBids() {
        return this.bids;
    }

    public List<Row> getAsks() {
        return this.asks;
    }

    public synchronized void bid(OrderVO vo) throws ServiceException {
        this.excute(vo, this.bidAction);
    }

    public synchronized void cancelBid(OrderVO vo) throws Exception {
        this.excute(vo, this.cancelBidAction);
    }

    public synchronized void ask(OrderVO vo) throws Exception {
        this.excute(vo, this.askAction);
    }

    public synchronized void cancelAsk(OrderVO vo) throws Exception {
        this.excute(vo, this.cancelAskAction);
    }

    public synchronized void sync(OrderVO vo) throws Exception {
        this.excute(vo, this.syncAction);
    }

    private synchronized void excute(OrderVO vo, Action action) throws ServiceException {
        action.excute(vo, this.bids, this.asks);
    }



}
