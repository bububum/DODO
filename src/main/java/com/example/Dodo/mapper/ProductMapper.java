package com.example.Dodo.mapper;

import com.example.Dodo.base.BaseMapper;
import com.example.Dodo.model.DTO.ProductDTO;
import com.example.Dodo.model.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface ProductMapper extends BaseMapper<Product, ProductDTO> {
}
