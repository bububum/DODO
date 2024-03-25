package com.example.Dodo.mapper;

import com.example.Dodo.base.BaseMapper;
import com.example.Dodo.model.DTO.OrderProductDTO;
import com.example.Dodo.model.entity.OrderProduct;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface OrderProductMapper extends BaseMapper<OrderProduct, OrderProductDTO> {
}
