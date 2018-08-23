/*
package com.socket.handlers_unused;

import com.account.session.AccountSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.InitializingBean;
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
public class SocketHandler extends TextWebSocketHandler implements InitializingBean {

    @Autowired
    @Qualifier("bitCoinTransactionService")
    TransactionService bitcoinService;

    @Autowired
    AccountSession accountSession;

    HashMap<WebSocketSession, WebSocketSession> map = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        session.sendMessage(new TextMessage(currentOrderLists()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
    }

    private String currentOrderLists() throws JsonProcessingException {
        Map response = new HashMap();

        Map orders = bitcoinService.getOrders();
        response.put("bitcoin", orders);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(response);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    public String sync(String message) throws Exception {
        Map map = new HashMap();
        Map orders = bitcoinService.getOrders();
        map.put("orders", orders);
        return objectMapper.writeValueAsString(map);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

*/
/*
    @Override
    public void afterPropertiesSet() throws Exception {

        if (!isTheThreadSet) {
            isTheThreadSet = true;
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        long start = System.currentTimeMillis();

                        loop();

                        long end = System.currentTimeMillis();
                        long result = end - start;
                        long sleepTime = result >= 2000 ? 0 : 2000 - result;
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }

    }
*//*


}

*/
