package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.OrderDTO;
import com.example.Dodo.model.response.OrderListResponse;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService extends BaseService<OrderDTO> {

    void changeOrderStatus();
}
