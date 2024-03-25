package com.example.Dodo.model.DTO;

import com.example.Dodo.base.BaseDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class AddressDTO extends BaseDTO {

    String street;
    String num;
    String comment;

    UserDTO user;
}
