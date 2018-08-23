package com.transaction.web;

import com.account.session.AccountSession;
import com.account.model.AccountVO;
import com.account.service.AccountService;
import com.account.token.TokenService;
import com.aspect.Authentication;
import com.aspect.EnoughBTC;
import com.aspect.EnoughMoney;
import com.core.OrderVO;
import com.exceptions.ErrorCode;
import com.exceptions.ErrorMessage;
import com.exceptions.ServiceException;
import com.socket.service.PushService;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BitcoinTransactionController {

    @Qualifier("bitCoinTransactionService")

    @Autowired
    TransactionService bitcoinService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountSession accountSession;

    @Autowired
    PushService pushService;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/bitcoin/orders.do", method = RequestMethod.POST)
    @ResponseBody
    public Map orders() {
        Map map = new HashMap();
        map.put("bitcoin", bitcoinService.getOrders());
        return map;
    }

    @RequestMapping(value = "/bitcoin/bid.do", method = RequestMethod.POST)
    @ResponseBody
    @Authentication
    @EnoughMoney
    public Map bid(@RequestBody @Valid OrderVO orderVO, BindingResult result) throws Exception {
        Map response = new HashMap();
        if (result.hasFieldErrors()) {
            throw new ServiceException(ErrorCode.UnknownError, ErrorMessage.UnknownError);
        } else {
            bitcoinService.bid(orderVO);
            AccountVO account = accountService.selectAccountByEmail(orderVO.getEmail());
            tokenService.pushNewTokenIntoAccount(account);
            pushService.noticeTransaction(orderVO.getTransactionHistory());
            response.put("account", account);
        }
        return response;
    }

    @RequestMapping(value = "/bitcoin/ask.do", method = RequestMethod.POST)
    @ResponseBody
    @Authentication
    @EnoughBTC
    public Map ask(@RequestBody @Valid OrderVO orderVO, BindingResult result) throws Exception {
        Map response = new HashMap();
        if (result.hasFieldErrors()) {
            throw new ServiceException(ErrorCode.UnknownError, ErrorMessage.UnknownError);
        } else {
            bitcoinService.ask(orderVO);
            AccountVO account = accountService.selectAccountByEmail(orderVO.getEmail());
            tokenService.pushNewTokenIntoAccount(account);
            pushService.noticeTransaction(orderVO.getTransactionHistory());
            response.put("account", account);
        }
        return response;
    }

    /**
     * bid and ask 에서 유저 세션정보 업데이트 해주고 판매 및 구매 체결 내용 푸쉬
     **/
    private void finalAction(OrderVO vo) throws Exception {

    }

    @RequestMapping(value = "/bitcoin/cancelOrder.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Map cancelOrder(@RequestBody OrderVO vo) throws Exception {
        Map result = new HashMap();
        return result;
    }

}

