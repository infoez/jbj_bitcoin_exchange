package com.core.databaseservice;


import com.common.dao.DaoService;
import com.core.*;
import com.exceptions.ErrorCode;
import com.exceptions.ErrorMessage;
import com.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BitcoinTransactionDatabaseService implements TransactionDatabaseService {

    private final String coinId = "A1";

    @Autowired
    DaoService daoService;

    @Override
    public void sync(List bids, List asks) {

        List<OrderVO> asksList = daoService.selectList("selectAsksForSync");
        List<OrderVO> bidsList = daoService.selectList("selectBidsForSync");

        this.sort(asksList, asks);
        this.sort(bidsList, bids);
    }

    private void sort(List<OrderVO> list, List target) {
        if (list.size() > 0) {
            String lastPrice = list.get(0).getPrice();
            Row row = new Row(lastPrice);
            int index = 0;
            for (OrderVO vo : list) {
                if (vo.getPrice().equals(lastPrice)) {
                    Order o = new Order(vo.getOrderId(), vo.getEmail(), vo.getQuantity());
                    row.add(o);
                } else {
                    target.add(row);
                    lastPrice = vo.getPrice();
                    row = new Row(vo.getPrice());
                    Order o = new Order(vo.getOrderId(), vo.getEmail(), vo.getQuantity());
                    row.add(o);
                    if (index == list.size() - 1)
                        target.add(row);
                }
                index++;
            }
        }
    }


    /** method 용도 : 구매 혹은 판매에서 요청자가 원하는 가격에 상응하는 물건이 존재 할 때 스왑 함. **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void trade(TradeVO vo) throws ServiceException {

        if (vo.getTranType().equals("bid")) {
            // 현금 확보
            daoService.update("krwToKrwWaiting", vo);
            if (!vo.isEnough())
                throw new ServiceException(ErrorCode.NotEnoughKRW, ErrorMessage.NotEnoughKRW);
        } else if (vo.getTranType().equals("ask")) {
            // 비트코인 확보
            daoService.update("btcToBtcWaiting", vo);
            if (!vo.isEnough())
                throw new ServiceException(ErrorCode.NotEnoguhBTC, ErrorMessage.NotEnoguhBTC);

        } else {
            throw new ServiceException(ErrorCode.UncertainTransactionType, ErrorMessage.UncertainTransactionType);
        }

        /** 거래 전 항상 krw_waiting or btc_waiting 처럼 waiting으로 옮겨놓고 그 돈으로 거래를 진행 한다. **/

        // 판매자 코인 다운 현금 업
        daoService.update("bitcoinTransaction.bitCoinSellerTransaction", vo);

        // 구매자 코인 업 현금 다운
        daoService.update("bitcoinTransaction.bitCoinBuyerTransaction", vo);

        // TB_BTC_ORDER의 quantity_left 감소 / 0이 되면 END_TRAN_YN = 'Y' TB_BTC_ORDER는 입찰 내역 Y가 아니라면 취소 가능
        daoService.update("bitcoinTransaction.orderTransAction", vo);

        // 최근 거래 insert TB_BTC_ORDER 키와 엮어서 저장; TB_BTC_ORDER의 실제 거래 내역
        daoService.insert("bitcoinTransaction.insertBitCoinRecentTransaction", vo);

    }

    /** 순수 구매 입찰. **/
    @Transactional(rollbackFor = Exception.class)
    public void bid(OrderVO vo) throws ServiceException {

        vo.setCoinId(this.coinId);

        // 사용자 계정 krw to krw_waiting
        daoService.update("bitcoinTransaction.krwToKrwWaiting", vo);
        if (!vo.isEnough())
            throw new ServiceException(ErrorCode.NotEnoughKRW, ErrorMessage.NotEnoughKRW);

        // 주문 목록에 대기열 추가;
        daoService.insert("bitcoinTransaction.insertOrder", vo);
        // 대기번호 받아오면 그 번호로 메모리에도 함께 등록 호출 메서드에서; bid 종료 후
    }

    /** 순수 판매 입찰. **/
    @Transactional(rollbackFor = Exception.class)
    public void ask(OrderVO vo) throws ServiceException {

        vo.setCoinId(this.coinId);

        // 사용자 계정 btc to btc_waiting
        daoService.update("bitcoinTransaction.btcToBtcWaiting", vo);
        if (!vo.isEnough())
            throw new ServiceException(ErrorCode.NotEnoguhBTC, ErrorMessage.NotEnoguhBTC);

        // 주문 목록에 대기열 추가
        daoService.insert("bitcoinTransaction.insertOrder", vo);
        // 대기번호 받아오면 그 번호로 메모리에도 함께 등록 호출 메서드에서; ask 종료 후
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO cancelBid(OrderVO vo) throws ServiceException {

        // 주문 목록에 대기열 삭제;
        int n = daoService.update("bitcoinTransaction.deleteBtcOrder", vo);

        if (n == 1) {

            com.transaction.model.OrderVO vo2 = (com.transaction.model.OrderVO) daoService.selectOne("bitcoinTransaction.selectBtcOrderByOrderId", vo);
            vo.setPrice(vo2.getPrice());
            vo.setQuantity(vo2.getQuantityLeft());

            daoService.update("bitcoinTransaction.krwWaitingToKrw", vo); // 사용자 계정 krw to krw_waiting

            if (!vo.isEnough())
                throw new ServiceException(ErrorCode.UnknownError, vo.getEmail() + " 입찰 금액이 취소 할 만큼 충분하지 않습니다.");
            return vo; // 삭제 되면 그 번호로 메모리에도 함께 삭제 호출 메서드에서; bid 종료 후

        } else {
            throw new ServiceException(ErrorCode.UnknownError, "취소 정보와 일치하는 항목이 존재 하지 않습니다. 취소자 : " + vo.getEmail() + ", 주문번호 : " + vo.getOrderId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO cancelAsk(OrderVO vo) throws ServiceException {
        // 사용자 계정 btc_waiting to btc
        int n = daoService.update("bitcoinTransaction.deleteBtcOrder", vo);

        if (n == 1) {
            com.transaction.model.OrderVO vo2 = (com.transaction.model.OrderVO) daoService.selectOne("bitcoinTransaction.selectBtcOrderByOrderId", vo);
            vo.setPrice(vo2.getPrice());
            vo.setQuantity(vo2.getQuantityLeft());

            daoService.update("bitcoinTransaction.btcWaitingToBtc", vo); // 사용자 계정 krw to krw_waiting

            if (!vo.isEnough())
                throw new ServiceException(ErrorCode.UnknownError, vo.getEmail() + " 대기 상태의 비트코인이 취소 할 만큼 충분하지 않습니다.");
            return vo; // 삭제 되면 그 번호로 메모리에도 함께 삭제 호출 메서드에서; bid 종료 후
        } else {
            throw new ServiceException(ErrorCode.UnknownError, "취소 정보와 일치하는 항목이 존재 하지 않습니다. 취소자 : " + vo.getEmail() + ", 주문번호 : " + vo.getOrderId());
        }
    }
}

