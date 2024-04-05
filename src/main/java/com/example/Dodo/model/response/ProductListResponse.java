package com.example.Dodo.model.response;

import com.example.Dodo.model.enums.PaymentType;

import java.time.LocalDateTime;

public interface ProductListResponse {

    Long getProductId();
    String getName();
    String getSize();

}
