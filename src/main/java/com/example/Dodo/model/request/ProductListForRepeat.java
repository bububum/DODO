package com.example.Dodo.model.request;

import com.example.Dodo.model.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProductListForRepeat {

    Long getId();
    BigDecimal getPrice();
    PaymentType getPaymentType();
    LocalDateTime getOrderDate();
    Long getAddressId();
}
