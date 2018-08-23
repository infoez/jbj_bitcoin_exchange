package com.transaction.service.impl;

import com.common.dao.DaoService;
import com.core.OrderVO;
import com.core.Row;
import com.core.Trade;
import com.exceptions.ServiceException;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitCoinTransactionServiceImpl implements TransactionService {

    @Autowired
    DaoService daoService;

    @Autowired
    Trade bitcoin;

    @Override
    public Map getOrders() {

        List<Row> asks = bitcoin.getAsks();
        List<Row> bids = bitcoin.getBids();

        int bidSize = bids.size();
        int askSize = asks.size();
        int size = asks.size() > bids.size() ? asks.size() : bids.size();
        int askCnt = 0;
        int bidCnt = 0;

        ArrayList askResult = new ArrayList();
        ArrayList bidResult = new ArrayList();

        for (int i = 0; i < size; i++) {

            askCnt = pushToResponse(asks, askSize, askCnt, askResult, i);

            bidCnt = pushToResponse(bids, bidSize, bidCnt, bidResult, i);

            if (askCnt >= 8 && bidCnt >= 8) break;
        }

        Map result = new HashMap<>();
        result.put("asks", askResult);
        result.put("bids", bidResult);

        return result;
    }

    private int pushToResponse(List<Row> list, int size, int cnt, List<Row> result, int i) {
        Row r;
        if (cnt < 8 && i < size) {
            r = list.get(i);
            if (r.isQueueContained()) {
                result.add(r);
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public void bid(OrderVO vo) throws ServiceException {
        bitcoin.bid(vo);
    }

    @Override
    public void ask(OrderVO vo) throws Exception {
        bitcoin.ask(vo);
    }



}
