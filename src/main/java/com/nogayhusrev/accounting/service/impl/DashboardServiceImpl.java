package com.nogayhusrev.accounting.service.impl;


import com.nogayhusrev.accounting.client.CurrencyClient;
import com.nogayhusrev.accounting.dto.CurrencyDto;
import com.nogayhusrev.accounting.dto.CurrencyResponse;
import com.nogayhusrev.accounting.dto.InvoiceDto;
import com.nogayhusrev.accounting.enums.InvoiceStatus;
import com.nogayhusrev.accounting.enums.InvoiceType;
import com.nogayhusrev.accounting.service.DashboardService;
import com.nogayhusrev.accounting.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final CurrencyClient currencyClient;
    private final InvoiceService invoiceService;

    public DashboardServiceImpl(CurrencyClient currencyClient, InvoiceService invoiceService) {
        this.currencyClient = currencyClient;
        this.invoiceService = invoiceService;
    }

    @Override
    public Map<String, BigDecimal> getSummaryNumbers() {
        Map<String, BigDecimal> summaryNumbersMap = new HashMap<>();
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal totalSales = BigDecimal.ZERO;
        BigDecimal profitLoss = BigDecimal.ZERO;
        List<InvoiceDto> allApprovedInvoicesOfCompany = invoiceService.findInvoiceByInvoiceStatus(InvoiceStatus.APPROVED);
        for (InvoiceDto invoice : allApprovedInvoicesOfCompany) {
            if (invoice.getInvoiceType() == InvoiceType.PURCHASE) {
                totalCost = totalCost.add(invoiceService.getTotalPriceOfInvoice(invoice.getId())).add(invoiceService.getTotalTaxOfInvoice(invoice.getId()));
            } else {
                totalSales = totalSales.add(invoiceService.getTotalPriceOfInvoice(invoice.getId())).add(invoiceService.getTotalTaxOfInvoice(invoice.getId()));
                profitLoss = profitLoss.add(invoiceService.getProfitLossOfInvoice(invoice.getId()));
            }
        }
        summaryNumbersMap.put("totalCost", totalCost);
        summaryNumbersMap.put("totalSales", totalSales);
        summaryNumbersMap.put("profitLoss", profitLoss);
        return summaryNumbersMap;
    }


    @Override
    public CurrencyDto getExchangeRates() {
        CurrencyResponse currency = currencyClient.getUsdBasedCurrencies();
        CurrencyDto currencyDto = CurrencyDto.builder()
                .euro(currency.getUsd().getEur())
                .britishPound(currency.getUsd().getGbp())
                .indianRupee(currency.getUsd().getInr())
                .japaneseYen(currency.getUsd().getJpy())
                .canadianDollar(currency.getUsd().getCad())
                .build();


        return currencyDto;
    }
}
