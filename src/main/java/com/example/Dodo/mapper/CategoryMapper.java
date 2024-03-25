package com.example.Dodo.mapper;

import com.example.Dodo.base.BaseMapper;
import com.example.Dodo.model.DTO.CategoryDTO;
import com.example.Dodo.model.entity.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, CategoryDTO> {
}
