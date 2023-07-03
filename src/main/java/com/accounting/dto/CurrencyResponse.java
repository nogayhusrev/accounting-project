package com.accounting.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {

    private LocalDate date;
    private Usd usd;


    @Data
    public static class Usd {
        private BigDecimal eur;
        private BigDecimal gbp;
        private BigDecimal inr;
        private BigDecimal jpy;
        private BigDecimal cad;

    }
}