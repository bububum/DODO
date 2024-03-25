package com.example.Dodo.model.DTO;

import com.example.Dodo.base.BaseDTO;
import com.example.Dodo.model.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ProductDTO extends BaseDTO {

    String name;
    String logo;
    String description;
    CategoryDTO category;
}
