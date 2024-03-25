package com.example.Dodo.mapper;

import com.example.Dodo.base.BaseMapper;
import com.example.Dodo.model.DTO.SizeDTO;
import com.example.Dodo.model.entity.Size;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface SizeMapper extends BaseMapper<Size, SizeDTO> {
}
