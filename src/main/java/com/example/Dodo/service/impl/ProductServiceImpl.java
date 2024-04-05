package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.ProductRepository;
import com.example.Dodo.mapper.ProductMapper;
import com.example.Dodo.model.DTO.ProductDTO;
import com.example.Dodo.model.entity.Product;
import com.example.Dodo.model.response.ProductListResponse;
import com.example.Dodo.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductRepository, ProductDTO, ProductMapper> implements ProductService {
    public ProductServiceImpl(ProductRepository rep, ProductMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public ProductDTO findByName(String name) {
        return mapper.toDto(rep.findByName(name), context);
    }

    @Override
    public List<ProductListResponse> getProductByFilter(Long sizeId, BigDecimal fromPrice, BigDecimal toPrice, String name, Long categoryId) {
        return rep.findByFilter(sizeId, fromPrice, toPrice, name, categoryId);
    }
}
