package com.example.Dodo.model.enums;

public enum PaymentType {

    CASH("Наличные"),
    CASHLESS("Безналичные"),
    CARD("Оплата картой курьеру");

    private final String def;

    PaymentType(String def) {
        this.def = def;
    }
}
