package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.ProductSizeDTO;
import com.example.Dodo.model.request.ProductCreateRequest;
import org.springframework.stereotype.Service;

public interface ProductSizeService extends BaseService<ProductSizeDTO> {

    String create (ProductCreateRequest request, Integer languageOrdinal);
}
