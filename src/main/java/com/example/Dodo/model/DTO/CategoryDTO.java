package com.example.Dodo.model.DTO;

import com.example.Dodo.base.BaseDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class CategoryDTO extends BaseDTO {

    String name;
    String description;
}
