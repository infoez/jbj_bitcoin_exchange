package com.core;

import com.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class BidAction implements Action {

    private final double satoshi = 0;
    private TransactionDatabaseService transactionDatabaseService;

    public BidAction(TransactionDatabaseService transactionDatabaseService) {
        this.transactionDatabaseService = transactionDatabaseService;
    }

    @Override
    public void excute(OrderVO vo, List<Row> bids, List<Row> asks) throws ServiceException {

        /** 입찰 전 입찰가보다 싼 판매물품이 있는
         *  경우 해당 물품을 먼저 구매 한 뒤
         *  남는 금액이 사토시보다 크면 입찰 **/
        List<Integer> search = this.search(vo, asks);

        if (search.size() == 0) {
            this.bid(vo, bids); /** 전체 입찰 **/
        } else {
            /** 입찰 전 구매 **/
            for (int i : search) { /** row 단위 **/
                Row row = asks.get(i);
                this.trade(row, vo); /** row 내부 queue 단위 ( 재귀 ) **/
                if (Calc.compare(vo.getQuantity(), String.valueOf(satoshi)) <= 0) { //vo.getQuantity() <= satoshi
                    break;
                }
            }

            /** 남은 양이 사토시보다 크면 입찰 **/
            if (Calc.compare(vo.getQuantity(), String.valueOf(satoshi)) > 0) //vo.getQuantity() > satoshi)
                this.bid(vo, bids);
        }
    }

    private List<Integer> search(OrderVO vo, List<Row> asks) {

        List result = new ArrayList();

        String askPrice = vo.getPrice();
        int index = 0;
        for (Row row : asks) {
            String price = row.getPrice();
            if (Calc.compare(price, askPrice) <= 0) {
                result.add(index);
            } else if (Calc.compare(price, askPrice) > 0){  //price > askPrice
                break;
            }
            index++;
        }
        return result;
    }

    void trade(Row row, OrderVO vo) throws ServiceException {

        Queue<Order> q = row.getQueue();

        if (q.size() > 0) {

            Order o = q.peek();

            String price = row.getPrice();

            String seller = o.getEmail();
            String qtLeft = o.getQuantity();

            String buyer = vo.getEmail();
            String askQt = vo.getQuantity();

            if (Calc.compare(qtLeft, askQt) >= 0) { //qtLeft >= askQt) {
                TradeVO tradeVO = new TradeVO();

                String coinAmount = askQt;
                String krwAmount = Calc.multiply(coinAmount, String.valueOf(price));

                tradeVO.setCoinId(vo.getCoinId());
                tradeVO.setQuantity(coinAmount);
                tradeVO.setKrwAmount(krwAmount);
                tradeVO.setBuyer(buyer);
                tradeVO.setSeller(seller);
                tradeVO.setPrice(price);
                tradeVO.setEmail(vo.getEmail());
                tradeVO.setTranType("bid");
                tradeVO.setOrderId(o.getOrderId());

                /** db update
                  * 판매자 askQt만큼 코인 차감
                  * 판매자 askQt * price 만큼 돈 상승;
                  * 구매자 askQt만큼 코인 상승
                  * 구매자 askQt * price 만큼 돈 차감;
                 **/
                transactionDatabaseService.trade(tradeVO);

                /** inform by socket
                 * 거래 체결 Push를 위해 입찰 전 종료 된 거래 내역을 저장 해 놓는다.
                 * 현재는 멀티 스레드가 접근 할 수 없으므로 일단 저장만 해놓고 스레드를 빠져 나간 뒤
                 * 컨트롤러에서 푸쉬를 한다.**/
                vo.addTransactionHistory(tradeVO);

                o.setQuantity(Calc.minus(qtLeft, askQt)); /** 남은 입찰 양 **/
                vo.setQuantity("0"); /** 남은 요구 양 0 이므로 다음 거래는 필요 없다. **/
                if (Calc.compare(o.getQuantity(), String.valueOf(satoshi)) <= 0) //o.getQuantity() <= satoshi
                /** ToDo : DB 입찰 취소 요청 **/
                    q.remove();

            } else { /** qtLeft < askQt **/

                String coinAmount = qtLeft;
                String krwAmount = Calc.multiply(coinAmount, String.valueOf(price)); //coinAmount * price;

                TradeVO tradeVO = new TradeVO();
                tradeVO.setQuantity(coinAmount);
                tradeVO.setKrwAmount(krwAmount);
                tradeVO.setBuyer(buyer);
                tradeVO.setSeller(seller);
                tradeVO.setPrice(price);
                tradeVO.setEmail(vo.getEmail());
                tradeVO.setTranType("bid");
                tradeVO.setOrderId(o.getOrderId());

                /** 판매자 askQt만큼 코인 차감
                 * 판매자 askQt * price 만큼 돈 상승
                 * 구매자 askQt만큼 코인 상승
                 * 구매자 askQt * price 만큼 돈 차감;
                 * **/
                transactionDatabaseService.trade(tradeVO);

                /** inform by socket
                 * 거래 체결 Push를 위해 입찰 전 종료 된 거래 내역을 저장 해 놓는다.
                 * 현재는 멀티 스레드가 접근 할 수 없으므로 일단 저장만 해놓고 스레드를 빠져 나간 뒤
                 * 컨트롤러에서 푸쉬를 한다.**/
                vo.addTransactionHistory(tradeVO);



                vo.setQuantity(Calc.minus(askQt, qtLeft)); //askQt - qtLeft); /** 요구한 양 만큼 거래를 못했으므로 다음 거래나 입찰로 넘어간다. **/
                o.setQuantity("0"); // 사실 이건 필요 없다. 바로 밑에줄에서 큐에서 제거가 될 것이기 때문.
                q.remove(); // o 에 들어있는 코인이 없으므로 다음 거래를 위해 큐에서 제거 한다.

                this.trade(row, vo);
            }
        }
    }

    void bid(OrderVO vo, List bids) throws ServiceException {

        String email = vo.getEmail();
        String price = vo.getPrice();
        String quantity = vo.getQuantity();


        if (bids.size() == 0) {

            /**
             * 배열 init when nothing in the list;
             * **/
            vo.setTranType("bid");
            transactionDatabaseService.bid(vo); // 1. DB 등록
            Order c = new Order(vo.getOrderId(), email, quantity);

            Row newRow = new Row(price);
            newRow.add(c);
            bids.add(newRow); // 2. Memory 등록

        } else {
            vo.setTranType("bid");
            transactionDatabaseService.bid(vo); // 1. DB 등록
            Order c = new Order(vo.getOrderId(), email, quantity);

            /**
             * 순서 정렬을 위한 과정..
             * ex) 1000원과 800원짜리가 각각 등록 되어 있는 상태에서
             *     1100원짜리 900원짜리 700원짜리가 등록 됨을 생각.
             *     1100, 1000, 900, 800, 700원 순서로 등록 되어져야 함
             * index (0)  (1)  (2)  (3)  (4)
             * **/
            for (int i = 0; i < bids.size(); i++) { // 2. Memory 등록

                Row row = (Row) bids.get(i);
                String rowPrice = row.getPrice();
                String compare = Calc.minus(rowPrice, price); // rowPrice - price;

                if (Calc.compare(compare, "0") == 0) { //compare == 0
                    row.add(c);
                    break;
                } else if (Calc.compare(compare, "0") < 0) { // compare < 0
                    Row newRow = new Row(price);
                    newRow.add(c);
                    bids.add(i, newRow);
                    break;
                } else if (i == bids.size() - 1) {
                    Row newRow = new Row(price);
                    newRow.add(c);
                    bids.add(newRow);
                    break;
                }
            }
        }
    }
}
