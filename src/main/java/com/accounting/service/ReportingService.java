package com.accounting.service;

import com.accounting.dto.InvoiceProductDto;
import com.accounting.dto.ProductDto;
import com.accounting.service.common.CrudService;

import java.util.List;

public interface ReportingService {

    List<InvoiceProductDto> getStock();

}
