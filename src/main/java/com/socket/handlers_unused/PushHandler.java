/*
package com.socket.handlers_unused;


import com.account.session.AccountSession;
import com.account.model.AccountVO;
import com.account.service.AccountService;
import com.core.TradeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;


@Component
public class PushHandler extends TextWebSocketHandler {

    @Autowired
    @Qualifier("bitCoinTransactionService")
    TransactionService bitcoinService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountSession accountSession;

    private HashMap<String, WebSocketSession> sessions = new HashMap<>();

    public PushHandler() {
        super();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        String query = session.getUri().getQuery();
        Map<String, String> qMap = this.queryToMap(query);
        String email = qMap.get("email");
        String token = qMap.get("token");
        if (accountSession.isTokenValid(email, token)) {
            sessions.put(email, session);
        } else {

        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
    }

    public void push(List<TradeVO> trades, String token) throws Exception {
        ObjectMapper om = new ObjectMapper();
        for (TradeVO trade : trades) {
            if (sessions.containsKey(trade.getSeller())) {
                WebSocketSession session = sessions.get(trade.getSeller());

                String msg = "거래가 " + trade.getPrice() + "에 수량" + trade.getQuantity() + "이 체결 되었습니다.";
                AccountVO accountVO = accountSession.getUser(trade.getSeller(), token);
                Map push = new HashMap();
                push.put("message", msg);
                push.put("account", accountVO);

                session.sendMessage(new TextMessage(om.writeValueAsString(push)));
            }
            if (sessions.containsKey(trade.getBuyer())) {
                WebSocketSession session = sessions.get(trade.getBuyer());

                String msg = "거래가 " + trade.getPrice() + "에 수량 " + trade.getQuantity() + "이 체결 되었습니다.";
                AccountVO accountVO = accountSession.getUser(trade.getBuyer(), token);
                Map push = new HashMap();
                push.put("message", msg);
                push.put("account", accountVO);

                session.sendMessage(new TextMessage(om.writeValueAsString(push)));
            }
        }
    }

    private Map queryToMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

}
*/
