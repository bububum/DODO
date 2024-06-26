package com.example.Dodo.model.request;

import com.example.Dodo.model.enums.PaymentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreateRequest {

    List<ProductOrderList> productOrderLists;

    PaymentType paymentType;
    Long addressId;
    LocalDateTime orderDate;

}
