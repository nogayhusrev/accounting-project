package com.accounting.service.impl;


import com.accounting.dto.InvoiceProductDto;
import com.accounting.entity.InvoiceProduct;
import com.accounting.enums.InvoiceStatus;
import com.accounting.service.InvoiceProductService;
import com.accounting.service.ReportingService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final InvoiceProductService invoiceProductService;

    public ReportingServiceImpl(InvoiceProductService invoiceProductService) {
        this.invoiceProductService = invoiceProductService;
    }


    @Override
    public List<InvoiceProductDto> getStock() {
       return invoiceProductService.findAll().stream()
               .filter(invoiceProductDto -> invoiceProductDto.getInvoice().getInvoiceStatus().equals(InvoiceStatus.APPROVED))
               .sorted(Comparator.comparing(InvoiceProductDto::getId).reversed())
               .collect(Collectors.toList());
    }
}
