package com.example.Dodo.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderHistoryResponse {

    Long id;
    LocalDateTime orderDate;
    String address;
    List<ProductHistoryResponse> list;
    BigDecimal price;
}
