package com.accounting.service;

import com.accounting.dto.ClientVendorDto;
import com.accounting.dto.InvoiceDto;
import com.accounting.enums.InvoiceType;
import com.accounting.service.common.CrudService;

import java.util.List;

public interface InvoiceService extends CrudService<InvoiceDto, Long> {

    List<InvoiceDto> findPurchaseInvoices();
    List<InvoiceDto> findSaleInvoices();
    List<ClientVendorDto> findVendors();
    List<ClientVendorDto> findClients();
    String generateInvoiceNo(InvoiceType invoiceType);
    InvoiceDto getNewInvoice(InvoiceType invoiceType);
    void save(InvoiceDto invoiceDto, InvoiceType invoiceType);
    void approve(Long invoiceId);

}
