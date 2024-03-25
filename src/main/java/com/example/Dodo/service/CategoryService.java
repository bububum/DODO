package com.example.Dodo.service;

import com.example.Dodo.base.BaseService;
import com.example.Dodo.model.DTO.CategoryDTO;
import com.example.Dodo.model.request.CategoryCreateRequest;
import org.springframework.stereotype.Service;

public interface CategoryService extends BaseService<CategoryDTO> {

    String create(CategoryCreateRequest request, Integer languageOrdinal);
}
