package com.nogayhusrev.accounting.controller;


import com.nogayhusrev.accounting.dto.InvoiceDto;
import com.nogayhusrev.accounting.dto.InvoiceProductDto;
import com.nogayhusrev.accounting.enums.InvoiceType;
import com.nogayhusrev.accounting.service.ClientVendorService;
import com.nogayhusrev.accounting.service.InvoiceProductService;
import com.nogayhusrev.accounting.service.InvoiceService;
import com.nogayhusrev.accounting.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/purchaseInvoices")
public class PurchaseInvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceProductService invoiceProductService;
    private final ClientVendorService clientVendorService;
    private final ProductService productService;


    public PurchaseInvoiceController(InvoiceService invoiceService, InvoiceProductService invoiceProductService, ClientVendorService clientVendorService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.invoiceProductService = invoiceProductService;
        this.clientVendorService = clientVendorService;
        this.productService = productService;
    }


    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("invoices", invoiceService.findPurchaseInvoices());

        return "/invoice/purchase-invoice-list";
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("newPurchaseInvoice", invoiceService.getNewInvoice(InvoiceType.PURCHASE));
        model.addAttribute("vendors", invoiceService.findVendors());


        return "/invoice/purchase-invoice-create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newPurchaseInvoice") InvoiceDto invoiceDto, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {

            model.addAttribute("newPurchaseInvoice", invoiceService.getNewInvoice(InvoiceType.PURCHASE));
            model.addAttribute("vendors", invoiceService.findVendors());

            return "/invoice/purchase-invoice-create";
        }


        invoiceService.save(invoiceDto, InvoiceType.PURCHASE);

        return "redirect:/purchaseInvoices/list";

    }

    @GetMapping("/update/{invoiceId}")
    public String update(@PathVariable("invoiceId") Long invoiceId, Model model) {

        model.addAttribute("invoice", invoiceService.findById(invoiceId));
        model.addAttribute("vendors", invoiceService.findVendors());

        model.addAttribute("newInvoiceProduct", new InvoiceProductDto());

        model.addAttribute("products", productService.findAll());
        model.addAttribute("invoiceProducts", invoiceProductService.findInvoiceProductsByInvoiceId(invoiceId));


        return "/invoice/purchase-invoice-update";

    }

    @PostMapping("/update/{invoiceId}")
    public String update(@Valid @ModelAttribute("invoice") InvoiceDto invoiceDto, BindingResult bindingResult, @PathVariable Long invoiceId, Model model) {


        if (bindingResult.hasErrors()) {

            return "redirect:/purchaseInvoices/update/" + invoiceId;
        }

        invoiceService.update(invoiceDto, invoiceId);
        return "redirect:/purchaseInvoices/list";
    }

    @GetMapping("/delete/{invoiceId}")
    public String delete(@PathVariable("invoiceId") Long invoiceId) {

        invoiceService.delete(invoiceId);

        return "redirect:/purchaseInvoices/list";
    }

    @GetMapping("/approve/{invoiceId}")
    public String approve(@PathVariable("invoiceId") Long invoiceId) {

        invoiceService.approve(invoiceId);

        return "redirect:/purchaseInvoices/list";
    }


    @PostMapping("/addInvoiceProduct/{invoiceId}")
    public String addInvoiceProductToPurchaseInvoice(@Valid @ModelAttribute("newInvoiceProduct") InvoiceProductDto invoiceProductDto, @PathVariable("invoiceId") Long invoiceId, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "redirect:/purchaseInvoices/update/" + invoiceId;
        }

        invoiceProductService.saveInvoiceProductByInvoiceId(invoiceProductDto, invoiceId);

        return "redirect:/purchaseInvoices/update/" + invoiceId;
    }

}
