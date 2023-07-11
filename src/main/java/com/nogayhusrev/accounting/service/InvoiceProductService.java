package com.nogayhusrev.accounting.service;

import com.nogayhusrev.accounting.dto.InvoiceProductDto;
import com.nogayhusrev.accounting.entity.InvoiceProduct;
import com.nogayhusrev.accounting.entity.Product;
import com.nogayhusrev.accounting.enums.InvoiceType;
import com.nogayhusrev.accounting.service.common.CrudService;

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
