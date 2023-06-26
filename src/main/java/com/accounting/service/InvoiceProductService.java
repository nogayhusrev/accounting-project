package com.accounting.service;

import com.accounting.dto.InvoiceProductDto;
import com.accounting.entity.InvoiceProduct;
import com.accounting.service.common.CrudService;

import java.util.List;

public interface InvoiceProductService extends CrudService<InvoiceProductDto, Long> {
    List<InvoiceProductDto> findInvoiceProductsByInvoiceId(Long invoiceId);
    void saveInvoiceProductByInvoiceId(InvoiceProductDto invoiceProductDto, Long invoiceId);

}
