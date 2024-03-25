package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.OrderProductRepository;
import com.example.Dodo.mapper.OrderProductMapper;
import com.example.Dodo.model.DTO.OrderProductDTO;
import com.example.Dodo.model.entity.OrderProduct;
import com.example.Dodo.service.OrderProductService;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl extends BaseServiceImpl<OrderProduct, OrderProductRepository, OrderProductDTO, OrderProductMapper> implements OrderProductService {
    public OrderProductServiceImpl(OrderProductRepository rep, OrderProductMapper mapper) {
        super(rep, mapper);
    }
}
