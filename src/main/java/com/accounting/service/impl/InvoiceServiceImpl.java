package com.accounting.service.impl;

import com.accounting.dto.ClientVendorDto;
import com.accounting.dto.InvoiceDto;
import com.accounting.entity.ClientVendor;
import com.accounting.entity.Company;
import com.accounting.entity.Invoice;
import com.accounting.enums.ClientVendorType;
import com.accounting.enums.InvoiceStatus;
import com.accounting.enums.InvoiceType;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.InvoiceRepository;
import com.accounting.service.ClientVendorService;
import com.accounting.service.InvoiceProductService;
import com.accounting.service.InvoiceService;
import com.accounting.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;
    private final ClientVendorService clientVendorService;
    private final InvoiceProductService invoiceProductService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, MapperUtil mapperUtil, UserService userService, ClientVendorService clientVendorService,@Lazy InvoiceProductService invoiceProductService) {
        this.invoiceRepository = invoiceRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
        this.clientVendorService = clientVendorService;
        this.invoiceProductService = invoiceProductService;
    }


    @Override
    public InvoiceDto findById(Long invoiceId) {
        InvoiceDto invoiceDto = mapperUtil.convert(invoiceRepository.findById(invoiceId).get(), new InvoiceDto());
        invoiceDto.setInvoiceProducts(new ArrayList<>());
        return invoiceDto;

    }

    @Override
    public List<InvoiceDto> findAll() {
        Company currentUserCompany = mapperUtil.convert(userService.getCurrentUser().getCompany(), new Company());

        return invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getCompany().getTitle().equalsIgnoreCase(currentUserCompany.getTitle()))
                .sorted(Comparator.comparing(Invoice::getInvoiceNo ).reversed())
                .map(invoice -> mapperUtil.convert(invoice, new InvoiceDto()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> findPurchaseInvoices() {
        return findAll().stream()
                .filter(invoice -> invoice.getInvoiceType().getValue().equals(InvoiceType.PURCHASE.getValue()))
                .map(invoiceDto -> {
                    invoiceDto.setInvoiceProducts(invoiceProductService.findInvoiceProductsByInvoiceId(invoiceDto.getId()));
                    return invoiceDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> findSaleInvoices() {
        return findAll().stream()
                .filter(invoice -> invoice.getInvoiceType().getValue().equals(InvoiceType.SALES.getValue()))
                .map(invoiceDto -> {
                    invoiceDto.setInvoiceProducts(invoiceProductService.findInvoiceProductsByInvoiceId(invoiceDto.getId()));
                    return invoiceDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendorDto> findVendors() {
        return clientVendorService.findAll().stream()
                .filter(clientVendorDto -> clientVendorDto.getClientVendorType().getValue().equalsIgnoreCase(ClientVendorType.VENDOR.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientVendorDto> findClients() {
        return clientVendorService.findAll().stream()
                .filter(clientVendorDto -> clientVendorDto.getClientVendorType().getValue().equalsIgnoreCase(ClientVendorType.CLIENT.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String generateInvoiceNo(InvoiceType invoiceType) {

        Company company = mapperUtil.convert(userService.getCurrentUser().getCompany(), new Company());
        List<Invoice> invoices = invoiceRepository.findInvoicesByCompanyAndInvoiceType(company, invoiceType);
        if (invoices.size() == 0) {
            return invoiceType.name().charAt(0) + "-001";
        }
        Invoice lastInvoiceOfTheCompany = invoices.stream()
                .max(Comparator.comparing(Invoice::getInsertDateTime)).get();
        int newOrder = Integer.parseInt(lastInvoiceOfTheCompany.getInvoiceNo().substring(2)) + 1;
        return invoiceType.name().charAt(0) + "-" + String.format("%03d", newOrder);

    }

    @Override
    public InvoiceDto getNewInvoice(InvoiceType invoiceType) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceNo(generateInvoiceNo(invoiceType));
        invoiceDto.setInvoiceType(invoiceType);
        invoiceDto.setDate(LocalDate.now());
        invoiceDto.setInvoiceProducts(new ArrayList<>());

        return invoiceDto;
    }

    @Override
    public void save(InvoiceDto invoiceDto, InvoiceType invoiceType) {
        invoiceDto.setInvoiceType(invoiceType);
        invoiceDto.setInvoiceProducts(new ArrayList<>());
        invoiceDto.setInvoiceStatus(InvoiceStatus.AWAITING_APPROVAL);
        invoiceDto.setCompany(userService.getCurrentUser().getCompany());
        invoiceRepository.save(mapperUtil.convert(invoiceDto,new Invoice()));

    }


    @Override
    public void save(InvoiceDto invoiceDto) {

        invoiceDto.setCompany(userService.getCurrentUser().getCompany());
        invoiceRepository.save(mapperUtil.convert(invoiceDto, new Invoice()));
    }

    @Override
    public void delete(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        invoiceProductService.findInvoiceProductsByInvoiceId(invoiceId)
                .forEach(invoiceProductDto -> invoiceProductService.delete(invoiceProductDto.getId()));
        invoice.setIsDeleted(true);
        invoiceRepository.save(invoice);
    }


    @Override
    public void update(InvoiceDto invoiceDto, Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        invoice.setClientVendor(mapperUtil.convert(invoiceDto.getClientVendor(), new ClientVendor()));
        invoiceRepository.save(invoice);

    }

    @Override
    public boolean isExist(InvoiceDto invoiceDto) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }


}
