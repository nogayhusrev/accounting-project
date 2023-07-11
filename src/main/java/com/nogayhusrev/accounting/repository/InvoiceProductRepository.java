package com.nogayhusrev.accounting.repository;

import com.nogayhusrev.accounting.entity.InvoiceProduct;
import com.nogayhusrev.accounting.entity.Product;
import com.nogayhusrev.accounting.enums.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {

    List<InvoiceProduct> findInvoiceProductsByInvoiceId(Long invoiceId);


    List<InvoiceProduct> findAllByInvoice_Id(Long invoiceId);

    List<InvoiceProduct> findInvoiceProductsByInvoiceInvoiceTypeAndProductAndRemainingQuantityNotOrderByIdAsc(InvoiceType type, Product product, Integer remainingQuantity);

    List<InvoiceProduct> findAllInvoiceProductByProductId(Long id);
}
