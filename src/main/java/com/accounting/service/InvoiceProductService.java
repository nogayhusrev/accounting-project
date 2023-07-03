package com.accounting.service;

import com.accounting.dto.InvoiceProductDto;
import com.accounting.entity.InvoiceProduct;
import com.accounting.entity.Product;
import com.accounting.enums.InvoiceType;
import com.accounting.service.common.CrudService;

import java.util.List;

public interface InvoiceProductService extends CrudService<InvoiceProductDto, Long> {
    List<InvoiceProductDto> findInvoiceProductsByInvoiceId(Long invoiceId);

    List<InvoiceProduct> findInvoiceProductsByInvoiceType(InvoiceType invoiceType);

    void saveInvoiceProductByInvoiceId(InvoiceProductDto invoiceProductDto, Long invoiceId);

    void completeApprovalProcedures(Long invoiceId, InvoiceType type);

    boolean checkProductQuantity(InvoiceProductDto salesInvoiceProduct);

    List<InvoiceProduct> findInvoiceProductsByInvoiceTypeAndProductRemainingQuantity(InvoiceType type, Product product, Integer remainingQuantity);
    List<InvoiceProduct> findAllInvoiceProductsByProductId(Long id);

}
