package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.OrderRepository;
import com.example.Dodo.mapper.OrderMapper;
import com.example.Dodo.model.DTO.OrderDTO;
import com.example.Dodo.model.entity.Order;
import com.example.Dodo.model.enums.OrderStatus;
import com.example.Dodo.model.response.OrderHistoryResponse;
import com.example.Dodo.model.response.OrderListResponse;
import com.example.Dodo.model.response.ProductHistoryResponse;
import com.example.Dodo.service.OrderService;
import com.example.Dodo.util.JwtProvider;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRepository, OrderDTO, OrderMapper> implements OrderService {
    public OrderServiceImpl(OrderRepository rep, OrderMapper mapper, JwtProvider jwtProvider) {
        super(rep, mapper);
        this.jwtProvider = jwtProvider;
    }

    private final JwtProvider jwtProvider;


    @Override
    public void changeOrderStatus() {

        List<OrderDTO> list = findAll();
        Long minutesPassed = 0L;
        for(OrderDTO i: list) {
            minutesPassed = Duration.between(i.getOrderDate(), LocalDateTime.now()).toMinutes();
            if (minutesPassed >= 30) {
                i.setOrderStatus(OrderStatus.PREPARING);
                update(i);
            }
        }

    }



}
