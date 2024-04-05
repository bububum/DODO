package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.ProductDTO;
import com.example.Dodo.model.request.ProductCreateRequest;
import com.example.Dodo.model.response.ProductListResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService extends BaseService<ProductDTO> {

    ProductDTO findByName(String name);
    List<ProductListResponse> getProductByFilter(Long sizeId, BigDecimal fromPrice, BigDecimal toPrice,  String name, Long categoryId);

}
