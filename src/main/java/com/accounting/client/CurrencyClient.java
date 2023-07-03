package com.accounting.client;

import com.accounting.dto.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/usd.json", name = "CURRENCY-CLIENT")
public interface CurrencyClient {

    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    CurrencyResponse getUsdBasedCurrencies();

}