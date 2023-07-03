package com.accounting.service;

import com.accounting.dto.InvoiceProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReportingService {

    List<InvoiceProductDto> getStock();

    Map<String, BigDecimal> getProfitLoss();

}
