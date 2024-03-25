package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.CategoryRepository;
import com.example.Dodo.exception.IncorrectRequest;
import com.example.Dodo.mapper.CategoryMapper;
import com.example.Dodo.model.DTO.CategoryDTO;
import com.example.Dodo.model.entity.Category;
import com.example.Dodo.model.request.CategoryCreateRequest;
import com.example.Dodo.service.CategoryService;
import com.example.Dodo.util.Language;
import com.example.Dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category, CategoryRepository, CategoryDTO, CategoryMapper> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository rep, CategoryMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public String create(CategoryCreateRequest request, Integer languageOrdinal) {

        Language language = Language.getLanguage(languageOrdinal);

        if (request != null) {

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(request.getName());
            categoryDTO.setDescription(request.getDescription());

            save(categoryDTO);

            return "Success";
        } else {
            throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "incorrectRequest"));
        }


    }
}
