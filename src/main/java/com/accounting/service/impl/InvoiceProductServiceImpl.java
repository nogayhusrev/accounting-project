package com.accounting.service.impl;

import com.accounting.dto.InvoiceProductDto;
import com.accounting.entity.Invoice;
import com.accounting.entity.InvoiceProduct;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.InvoiceProductRepository;
import com.accounting.service.InvoiceProductService;
import com.accounting.service.InvoiceService;
import com.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

    private final InvoiceProductRepository invoiceProductRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;
    private final InvoiceService invoiceService;

    public InvoiceProductServiceImpl(InvoiceProductRepository invoiceProductRepository, MapperUtil mapperUtil, UserService userService, InvoiceService invoiceService) {
        this.invoiceProductRepository = invoiceProductRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
        this.invoiceService = invoiceService;
    }


    @Override
    public List<InvoiceProductDto> findInvoiceProductsByInvoiceId(Long invoiceId) {
        return invoiceProductRepository.findInvoiceProductsByInvoiceId(invoiceId).stream()
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveInvoiceProductByInvoiceId(InvoiceProductDto invoiceProductDto, Long invoiceId) {
        Invoice invoice = mapperUtil.convert(invoiceService.findById(invoiceId), new Invoice());
        InvoiceProduct invoiceProduct = mapperUtil.convert(invoiceProductDto, new InvoiceProduct());
        invoiceProduct.setInvoice(invoice);
        invoiceProduct.setProfitLoss(BigDecimal.ZERO);
        save(mapperUtil.convert(invoiceProduct,new InvoiceProductDto()));
    }

    @Override
    public InvoiceProductDto findById(Long invoiceProductId) {
        return mapperUtil.convert(invoiceProductRepository.findById(invoiceProductId), new InvoiceProductDto());
    }

    @Override
    public List<InvoiceProductDto> findAll() {
        return invoiceProductRepository.findAll().stream()
                .map(invoiceProduct -> mapperUtil.convert(invoiceProduct, new InvoiceProductDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(InvoiceProductDto invoiceProductDto) {
        invoiceProductRepository.save(mapperUtil.convert(invoiceProductDto, new InvoiceProduct()));
    }

    public void delete(Long invoiceProductId) {
        InvoiceProduct invoiceProduct = invoiceProductRepository.findById(invoiceProductId).get();
        invoiceProduct.setIsDeleted(true);
        invoiceProductRepository.save(invoiceProduct);
    }

    @Override
    public void update(InvoiceProductDto invoiceProductDto, Long aLong) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }

    @Override
    public boolean isExist(InvoiceProductDto invoiceProductDto) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }
}
