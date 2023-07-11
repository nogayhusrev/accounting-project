package com.nogayhusrev.accounting.service.impl;


import com.nogayhusrev.accounting.dto.InvoiceProductDto;
import com.nogayhusrev.accounting.entity.InvoiceProduct;
import com.nogayhusrev.accounting.enums.InvoiceStatus;
import com.nogayhusrev.accounting.enums.InvoiceType;
import com.nogayhusrev.accounting.mapper.MapperUtil;
import com.nogayhusrev.accounting.service.InvoiceProductService;
import com.nogayhusrev.accounting.service.ReportingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final InvoiceProductService invoiceProductService;

    private final MapperUtil mapperUtil;

    public ReportingServiceImpl(InvoiceProductService invoiceProductService, MapperUtil mapperUtil) {
        this.invoiceProductService = invoiceProductService;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public List<InvoiceProductDto> getStock() {
        return invoiceProductService.findAll().stream()
                .filter(invoiceProductDto -> invoiceProductDto.getInvoice().getInvoiceStatus().equals(InvoiceStatus.APPROVED))
                .sorted(Comparator.comparing(InvoiceProductDto::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, BigDecimal> getProfitLoss() {
        Map<String, BigDecimal> profitLossMap = new HashMap<>();

        List<InvoiceProduct> salesInvoiceProducts = invoiceProductService.findInvoiceProductsByInvoiceType(InvoiceType.SALES);

        for (InvoiceProduct invoiceProduct : salesInvoiceProducts) {
            int year = invoiceProduct.getInvoice().getDate().getYear();
            String month = invoiceProduct.getInvoice().getDate().getMonth().toString();
            BigDecimal profitLoss = invoiceProduct.getProfitLoss();
            String timeWindow = year + " " + month;
            profitLossMap.put(timeWindow, profitLossMap.getOrDefault(timeWindow, BigDecimal.ZERO).add(profitLoss));
        }
        return profitLossMap;
    }


}
