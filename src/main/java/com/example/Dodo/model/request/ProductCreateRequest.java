package com.example.Dodo.model.request;

import com.example.Dodo.model.DTO.CategoryDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreateRequest {

    String name;
    String logo;
    String description;
    Long categoryId;
    Long sizeId;
    BigDecimal price;
}
