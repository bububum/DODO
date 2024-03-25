package com.example.Dodo.mapper;

import com.example.Dodo.base.BaseMapper;
import com.example.Dodo.model.DTO.OrderDTO;
import com.example.Dodo.model.entity.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderDTO> {
}
