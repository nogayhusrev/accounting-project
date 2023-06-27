package com.accounting.service.impl;


import com.accounting.dto.InvoiceProductDto;
import com.accounting.entity.Company;
import com.accounting.entity.InvoiceProduct;
import com.accounting.enums.InvoiceStatus;
import com.accounting.enums.InvoiceType;
import com.accounting.mapper.MapperUtil;
import com.accounting.service.InvoiceProductService;
import com.accounting.service.ReportingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
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
