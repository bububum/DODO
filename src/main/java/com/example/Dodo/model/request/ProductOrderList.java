package com.example.Dodo.model.request;

import com.example.Dodo.model.DTO.ProductSizeDTO;
import com.example.Dodo.model.entity.ProductSize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOrderList {

    Long id;
    BigDecimal price;
}
