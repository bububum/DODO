package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.ProductDTO;
import com.example.Dodo.model.request.ProductCreateRequest;
import org.springframework.stereotype.Service;

public interface ProductService extends BaseService<ProductDTO> {

    ProductDTO findByName(String name);

}
