package com.core;

import com.exceptions.ServiceException;

import java.util.List;

class SyncAction implements Action{

    private TransactionDatabaseService transactionDatabaseService;

    public SyncAction(TransactionDatabaseService transactionDatabaseService) {
        this.transactionDatabaseService = transactionDatabaseService;
    }

    @Override
    public void excute(OrderVO vo, List<Row> bids, List<Row> asks) throws ServiceException {
        transactionDatabaseService.sync(bids, asks);
    }

}
