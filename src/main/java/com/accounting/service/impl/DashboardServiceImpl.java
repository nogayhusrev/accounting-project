package com.accounting.service.impl;


import com.accounting.client.CurrencyClient;
import com.accounting.dto.CurrencyDto;
import com.accounting.dto.CurrencyResponse;
import com.accounting.service.DashboardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final CurrencyClient currencyClient;

    public DashboardServiceImpl(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Override
    public Map<String, BigDecimal> getSummaryNumbers() {
        return null;
    }

    @Override
    public CurrencyDto getExchangeRates() {
        CurrencyResponse currency = currencyClient.getUsdBasedCurrencies();
        CurrencyDto currencyDto= CurrencyDto.builder()
                .euro(currency.getUsd().getEur())
                .britishPound(currency.getUsd().getGbp())
                .indianRupee(currency.getUsd().getInr())
                .japaneseYen(currency.getUsd().getJpy())
                .canadianDollar(currency.getUsd().getCad())
                .build();


        return currencyDto;
    }
}
