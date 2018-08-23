package com.init;

import com.core.OrderVO;
import com.core.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class ApplicationInitEvent implements ApplicationListener {

    @Autowired
    Trade bitcoin;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(bitcoin.getAsks().size() == 0 && bitcoin.getBids().size() == 0){
            try {
                bitcoin.sync(new OrderVO());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
