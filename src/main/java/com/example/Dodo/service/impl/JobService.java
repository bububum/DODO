package com.example.Dodo.service.impl;

import com.example.Dodo.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component

public class JobService {

    private final OrderService orderService;

    public JobService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 600000) // 30000 миллисекунд = 30 секунд
    private void changingOrderStatus(){

        orderService.changeOrderStatus();
        System.out.println(LocalDateTime.now());
    }
}