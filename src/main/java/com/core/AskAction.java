package com.core;

import com.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class AskAction implements Action {

    private final double satoshi = 0;
    private TransactionDatabaseService transactionDatabaseService;

    public AskAction(TransactionDatabaseService transactionDatabaseService) {
        this.transactionDatabaseService = transactionDatabaseService;
    }

    @Override
    public void excute(OrderVO vo, List<Row> bids, List<Row> asks) throws ServiceException {

        List<Integer> search = this.search(vo, bids);

        if (search.size() == 0) {
            this.ask(vo, asks); //전체 입찰
        } else {
            /** 입찰 전 판매 **/
            for (int i : search) { // row 단위
                Row row = bids.get(i);
                this.trade(row, vo); // row 내부 queue 단위
                if (Calc.compare(vo.getQuantity(), String.valueOf(satoshi)) <= 0) { //vo.getQuantity() <= satoshi
                    break;
                }
            }

            /** 남은 양이 사토시보다 크면 입찰 **/
            if (Calc.compare(vo.getQuantity(), String.valueOf(satoshi)) > 0)//vo.getQuantity() > satoshi
                this.ask(vo, asks);
        }
    }

    /**
     * @param ReqTransVO vo, List<Row> asks
     * @return 판매 요청을 하는 가격보다 높은 가격에 Bidding 되어 있는 bids 인덱스 목록 리스트
     */
    private List<Integer> search(OrderVO vo, List<Row> asks) {

        List result = new ArrayList();

        String askPrice = vo.getPrice();
        int index = 0;
        for (Row row : asks) {
            String price = row.getPrice();
            if (Calc.compare(price, askPrice) >= 0) {// price >= askPrice
                if (row.getQueue().size() > 0)
                    result.add(index);
            } else if (Calc.compare(price, askPrice) < 0) { // price < askPrice
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

            String seller = vo.getEmail();
            String askQt = vo.getQuantity();

            String buyer = o.getEmail();
            String qtLeft = o.getQuantity();

            if (Calc.compare(qtLeft, askQt) >= 0) { // qtLeft >= askQt

                String coinAmount = askQt;

                TradeVO tradeVO = new TradeVO();
                tradeVO.setQuantity(coinAmount);
                tradeVO.setBuyer(buyer);
                tradeVO.setSeller(seller);
                tradeVO.setPrice(price);
                tradeVO.setEmail(vo.getEmail());
                tradeVO.setTranType("ask");
                tradeVO.setOrderId(o.getOrderId());

                /** ToDo : db update **/
                transactionDatabaseService.trade(tradeVO);

                /** ToDo : if success **/
                /** ToDo : inform by socket **/
                /** 거래 체결 Push를 위해 입찰 전 종료 된 거래 내역을 저장 해 놓는다.
                 * 현재는 멀티 스레드가 접근 할 수 없으므로 일단 저장만 해놓고 스레드를 빠져 나간 뒤
                 * 컨트롤러에서 푸쉬를 한다.**/
                vo.addTransactionHistory(tradeVO);

                o.setQuantity(Calc.minus(qtLeft, askQt)); /** 남은 입찰 양 **/
                vo.setQuantity("0"); /** 남은 요구 양 0 이므로 다음 거래는 필요 없다. **/


                /** 메모리 정리 하고 종료 **/
                if (Calc.compare(o.getQuantity(), String.valueOf(satoshi)) <= 0) //o.getQuantity() <= satoshi
                    q.remove();

            } else { /** qtLeft < askQt **/

                String coinAmount = qtLeft;

                TradeVO tradeVO = new TradeVO();
                tradeVO.setQuantity(coinAmount);
                tradeVO.setBuyer(buyer);
                tradeVO.setSeller(seller);
                tradeVO.setPrice(price);
                tradeVO.setEmail(vo.getEmail());
                tradeVO.setTranType("ask");
                tradeVO.setOrderId(o.getOrderId());

                /** ToDo : db update **/
                transactionDatabaseService.trade(tradeVO);

                /** ToDo : if success **/
                /** ToDo : inform by socket **/
                /** 거래 체결 Push를 위해 입찰 전 종료 된 거래 내역을 저장 해 놓는다.
                 * 현재는 멀티 스레드가 접근 할 수 없으므로 일단 저장만 해놓고 스레드를 빠져 나간 뒤
                 * 컨트롤러에서 푸쉬를 한다.**/
                vo.addTransactionHistory(tradeVO);

                /** 메모리 정리 **/
                vo.setQuantity(Calc.minus(vo.getQuantity(), o.getQuantity()));//vo.getQuantity() - o.getQuantity();
                q.remove();

                /** 아직 남아 있으므로 한번 더 **/
                this.trade(row, vo);
            }
        }
    }

    void ask(OrderVO vo, List asks) throws ServiceException {

        String email = vo.getEmail();
        String price = vo.getPrice();
        String quantity = vo.getQuantity();
        vo.setTranType("ask");

        transactionDatabaseService.ask(vo); // 1. DB 등록 2. insert 전에 orderid 받아서 vo 에 setting 함. 3. 그 값을 가져와서 아래 메모리에 함께 입력

        if (asks.size() == 0) {
            Order c = new Order(vo.getOrderId(), email, quantity);
            Row newRow = new Row(price);
            newRow.add(c);
            asks.add(newRow);
        } else {
            Order c = new Order(vo.getOrderId(), email, quantity);

            for (int i = 0; i < asks.size(); i++) {

                Row row = (Row) asks.get(i);
                String rowPrice = row.getPrice();
                String compare = Calc.minus(rowPrice, price);//rowPrice - price;

                if (Calc.compare(compare, "0") == 0){//(compare == 0) {
                    row.add(c);
                    break;
                } else if (Calc.compare(compare, "0") > 0) { //compare > 0
                    Row newRow = new Row(price);
                    newRow.add(c);
                    asks.add(i, newRow);
                    break;
                } else if (i == asks.size() - 1) {
                    Row newRow = new Row(price);
                    newRow.add(c);
                    asks.add(newRow);
                    break;
                }
            } // end of loop

        }
    }

}
