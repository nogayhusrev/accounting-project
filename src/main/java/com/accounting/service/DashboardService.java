package com.accounting.service;

import com.accounting.dto.CurrencyDto;

import java.math.BigDecimal;
import java.util.Map;

public interface DashboardService {

    Map<String, BigDecimal> getSummaryNumbers();
    CurrencyDto getExchangeRates();
}
