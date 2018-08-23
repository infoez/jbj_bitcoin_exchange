package com.socket.demon;

import com.socket.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class OrdersPushCron {

    @Autowired
    PushService pushService;

    @Scheduled(fixedDelay = 500)
    public void fuck() throws Exception {

        /*pushService.broadCast();*/
    }

}


