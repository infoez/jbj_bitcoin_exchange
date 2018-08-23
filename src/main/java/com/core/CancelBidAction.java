package com.core;

import com.exceptions.ServiceException;

import java.util.List;

class CancelBidAction implements Action {

    private TransactionDatabaseService transactionDatabaseService;

    public CancelBidAction(TransactionDatabaseService transactionDatabaseService) {
        this.transactionDatabaseService = transactionDatabaseService;
    }

    @Override
    public void excute(OrderVO vo, List<Row> bids, List<Row> asks) throws ServiceException {

        OrderVO cancel = transactionDatabaseService.cancelBid(vo);

        String price = cancel.getPrice();
        String orderId = cancel.getOrderId();

        Row rowLookingFor = null;
        for (Row row : bids) {
            if (price.equals(row.getPrice())) {
                rowLookingFor = row;
                break;
            }
        }

        if (rowLookingFor != null)
            removeQueue(orderId, rowLookingFor);

    }

    private void removeQueue(String orderId, Row row) {
        row.getQueue().removeIf((o) -> {
            if (o.getOrderId().equals(orderId))
                return true;
            else
                return false;
        });
    }
}
