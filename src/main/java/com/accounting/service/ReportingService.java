package com.accounting.service;

import com.accounting.dto.InvoiceProductDto;
import com.accounting.dto.ProductDto;
import com.accounting.service.common.CrudService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReportingService {

    List<InvoiceProductDto> getStock();
    Map<String, BigDecimal> getProfitLoss();

}
