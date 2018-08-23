package com.socket.service;

import com.account.model.AccountVO;
import com.account.service.AccountService;
import com.core.TradeVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PushServiceImpl implements PushService {

    @Autowired
    SimpMessageSendingOperations push;

    @Autowired
    @Qualifier("bitCoinTransactionService")
    TransactionService bitcoinService;

    @Autowired
    AccountService accountService;

    private final String transactionPushAddress = "/push/transaction/";
    private final String ordersPushAddress = "/push/orders";

    @Override
    public void broadCast() throws Exception {
        String orders = this.currentOrderLists();
        push.convertAndSend(this.ordersPushAddress, orders);
    }

    @Override
    public void noticeTransaction(List<TradeVO> trades) {

        HashMap<String, List> result = new HashMap(); // 이메일 및 트랜잭션 리스트

        if (trades.size() > 0) {

            this.pushIntoResultMapOrganized(trades, result); // 정리해서 넣는다.

            Set keys = result.keySet(); // 이메일 다발
            List<AccountVO> accountVOList = accountService.selectAccountListByEmails(new ArrayList<String>(keys)); // 한번에 가져오기 위해

            for (AccountVO accountVO : accountVOList) { // 각각의 유저에게 통보
                Map body = new HashMap();
                body.put("transactions", result.get(accountVO.getEmail()));
                body.put("account", accountVO);
                push.convertAndSend(transactionPushAddress + accountVO.getEmail(), body);
            }
        }
    }
    
    private void pushIntoResultMapOrganized(List<TradeVO> trades, HashMap<String, List> result) {
        for (TradeVO tradeVO : trades) {
            String buyer = tradeVO.getBuyer();
            String seller = tradeVO.getSeller();
            this.organize(result, buyer, tradeVO);
            this.organize(result, seller, tradeVO);
        }
    }

    private void organize(Map<String, List> map, String key, TradeVO tradeVO) {
        if (map.containsKey(key)) {
            map.get(key).add(key);
        } else {
            ArrayList list = new ArrayList();
            list.add(tradeVO);
            map.put(key, list);
        }
    }

    private String currentOrderLists() throws JsonProcessingException {
        Map response = new HashMap();
        Map orders = bitcoinService.getOrders();
        response.put("bitcoin", orders);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }

}
