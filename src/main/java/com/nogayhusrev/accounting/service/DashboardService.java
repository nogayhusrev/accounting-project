package com.nogayhusrev.accounting.service;

import com.nogayhusrev.accounting.dto.CurrencyDto;

import java.math.BigDecimal;
import java.util.Map;

public interface DashboardService {

    Map<String, BigDecimal> getSummaryNumbers();

    CurrencyDto getExchangeRates();
}
