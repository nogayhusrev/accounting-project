package com.accounting.controller;


import com.accounting.dto.InvoiceDto;
import com.accounting.dto.InvoiceProductDto;
import com.accounting.enums.InvoiceType;
import com.accounting.service.ClientVendorService;
import com.accounting.service.InvoiceProductService;
import com.accounting.service.InvoiceService;
import com.accounting.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/salesInvoices")
public class SaleInvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;


    public SaleInvoiceController(InvoiceService invoiceService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
    }


    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("invoices", invoiceService.findSaleInvoices());

        return "/invoice/sales-invoice-list";
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("newSalesInvoice", invoiceService.getNewInvoice(InvoiceType.SALES));
        model.addAttribute("clients", invoiceService.findClients());


        return "/invoice/sales-invoice-create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newSalesInvoice") InvoiceDto invoiceDto, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {

            return "/invoice/sales-invoice-create";
        }


        invoiceService.save(invoiceDto, InvoiceType.SALES);

        return "redirect:/salesInvoices/list";

    }

    @GetMapping("/update/{invoiceId}")
    public String update(@PathVariable("invoiceId") Long invoiceId, Model model) {

        model.addAttribute("invoice", invoiceService.findById(invoiceId));
        model.addAttribute("clients", invoiceService.findClients());

        model.addAttribute("newInvoiceProduct", new InvoiceProductDto());

        model.addAttribute("products", productService.findAll());
        model.addAttribute("invoiceProducts", invoiceProductService.findInvoiceProductsByInvoiceId(invoiceId));


        return "/invoice/sales-invoice-update";

    }

    @PostMapping("/update/{invoiceId}")
    public String update(@Valid @ModelAttribute("invoice") InvoiceDto invoiceDto, BindingResult bindingResult, @PathVariable Long invoiceId, Model model) {


        if (bindingResult.hasErrors()) {


            return "redirect:/salesInvoices/update/" + invoiceId;
        }

        invoiceService.update(invoiceDto, invoiceId);
        return "redirect:/salesInvoices/list";
    }

    @GetMapping("/delete/{invoiceId}")
    public String delete(@PathVariable("invoiceId") Long invoiceId) {

        invoiceService.delete(invoiceId);

        return "redirect:/salesInvoices/list";
    }

    @GetMapping("/approve/{invoiceId}")
    public String approve(@PathVariable("invoiceId") Long invoiceId) {

        invoiceService.approve(invoiceId);

        return "redirect:/salesInvoices/list";
    }


    @PostMapping("/addInvoiceProduct/{invoiceId}")
    public String addInvoiceProductToPurchaseInvoice(@Valid @ModelAttribute("newInvoiceProduct") InvoiceProductDto invoiceProductDto, @PathVariable("invoiceId") Long invoiceId, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "redirect:/salesInvoices/update/" + invoiceId;
        }

        invoiceProductService.saveInvoiceProductByInvoiceId(invoiceProductDto, invoiceId);

        return "redirect:/salesInvoices/update/" + invoiceId;
    }

}
