package com.example.Dodo.model.DTO;

import com.example.Dodo.base.BaseDTO;
import com.example.Dodo.model.entity.Order;
import com.example.Dodo.model.entity.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class OrderProductDTO extends BaseDTO {

    OrderDTO order;
    ProductDTO product;
}
