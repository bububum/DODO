package com.example.Dodo.model.response;

import com.example.Dodo.model.DTO.ProductSizeDTO;
import com.example.Dodo.model.entity.ProductSize;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderListResponse {

    Long getId();
    LocalDateTime getOrderDate();
    String getAddress();
    BigDecimal getPrice();


}
