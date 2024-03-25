package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.ProductRepository;
import com.example.Dodo.mapper.ProductMapper;
import com.example.Dodo.microservices.FileService;
import com.example.Dodo.model.DTO.ProductDTO;
import com.example.Dodo.model.entity.Product;
import com.example.Dodo.model.request.ProductCreateRequest;
import com.example.Dodo.model.response.FileServiceUploadResponse;
import com.example.Dodo.service.CategoryService;
import com.example.Dodo.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductRepository, ProductDTO, ProductMapper> implements ProductService {
    public ProductServiceImpl(ProductRepository rep, ProductMapper mapper) {
        super(rep, mapper);
    }

}
