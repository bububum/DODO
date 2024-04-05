package com.example.Dodo.model.request;

import com.example.Dodo.model.enums.PaymentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRepeatRequest {

    PaymentType paymentType;
    Long addressId;
    LocalDateTime orderDate;
}
