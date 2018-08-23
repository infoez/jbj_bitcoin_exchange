package com.socket.service;

import com.core.TradeVO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface PushService {

    void broadCast() throws JsonProcessingException, Exception;

    void noticeTransaction(List<TradeVO> trades) throws Exception;
}
