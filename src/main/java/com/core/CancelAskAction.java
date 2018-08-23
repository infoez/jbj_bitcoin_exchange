package com.core;

import com.exceptions.ServiceException;

import java.util.List;

class CancelAskAction implements Action {

    private TransactionDatabaseService transactionDatabaseService;

    public CancelAskAction(TransactionDatabaseService transactionDatabaseService) {
        this.transactionDatabaseService = transactionDatabaseService;
    }

    @Override
    public void excute(OrderVO vo, List<Row> bids, List<Row> asks) throws ServiceException {
        OrderVO cancel = transactionDatabaseService.cancelAsk(vo);

        String price = cancel.getPrice();
        String orderId = cancel.getOrderId();

        Row rowLookingFor = null;
        for (Row row : asks) {
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
