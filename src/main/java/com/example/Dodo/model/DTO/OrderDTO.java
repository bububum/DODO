package com.example.Dodo.model.DTO;

import com.example.Dodo.base.BaseDTO;
import com.example.Dodo.model.entity.User;
import com.example.Dodo.model.enums.OrderStatus;
import com.example.Dodo.model.enums.PaymentType;
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
    BigDecimal discount;
    LocalDateTime orderDate;
    PaymentType paymentType;
    OrderStatus orderStatus;
    UserDTO user;
    AddressDTO address;
}
