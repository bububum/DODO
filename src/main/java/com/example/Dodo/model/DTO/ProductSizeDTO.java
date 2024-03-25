package com.example.Dodo.model.DTO;

import com.example.Dodo.base.BaseDTO;
import com.example.Dodo.model.entity.Product;
import com.example.Dodo.model.entity.Size;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ProductSizeDTO extends BaseDTO {

    ProductDTO product;
    SizeDTO size;
    BigDecimal price;

}
