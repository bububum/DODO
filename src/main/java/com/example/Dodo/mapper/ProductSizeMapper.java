package com.example.Dodo.mapper;

import com.example.Dodo.base.BaseMapper;
import com.example.Dodo.model.DTO.ProductSizeDTO;
import com.example.Dodo.model.entity.ProductSize;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface ProductSizeMapper extends BaseMapper<ProductSize, ProductSizeDTO> {
}
