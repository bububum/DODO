package com.example.Dodo.model.DTO;

import com.example.Dodo.base.BaseDTO;
import com.example.Dodo.model.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class OrderDTO extends BaseDTO {

    BigDecimal price;
    Integer dodocoins;
    LocalDateTime orderDate;
    UserDTO user;
    AddressDTO address;
}
