package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.OrderRepository;
import com.example.Dodo.mapper.OrderMapper;
import com.example.Dodo.model.DTO.OrderDTO;
import com.example.Dodo.model.entity.Order;
import com.example.Dodo.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRepository, OrderDTO, OrderMapper> implements OrderService {
    public OrderServiceImpl(OrderRepository rep, OrderMapper mapper) {
        super(rep, mapper);
    }
}
