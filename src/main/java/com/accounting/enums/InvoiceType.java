package com.accounting.enums;

import lombok.Getter;

@Getter
public enum InvoiceType {
    PURCHASE("Purchase"),
    SALES("Sales");


    private final String value;

    InvoiceType(String value) {
        this.value = value;
    }
}
