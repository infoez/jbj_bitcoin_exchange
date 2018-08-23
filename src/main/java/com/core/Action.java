package com.core;

import com.exceptions.ServiceException;

import java.util.List;

interface Action {

    void excute(OrderVO vo, List<Row> bids, List<Row> asks) throws ServiceException;

}
