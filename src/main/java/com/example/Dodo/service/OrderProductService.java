package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.OrderDTO;
import com.example.Dodo.model.DTO.OrderProductDTO;
import com.example.Dodo.model.request.OrderCreateRequest;
import com.example.Dodo.model.request.OrderRepeatRequest;
import com.example.Dodo.model.response.OrderHistoryResponse;
import com.example.Dodo.model.response.OrderListResponse;
import com.example.Dodo.model.response.ProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderProductService extends BaseService<OrderProductDTO> {

    String create(OrderCreateRequest request, Integer languageOrdinal, String accessToken);
    OrderCreateRequest repeatOrder(Long orderId, String accessToken, Integer languageOrdinal);
    List<OrderProductDTO> getByOrderId(Long orderId);
    List<ProductListResponse> historyOfProductsByOrder(Long orderId);
    Page<OrderHistoryResponse> historyOfOrders(String accessToken, Integer languageOrdinal, Integer limit);
}
