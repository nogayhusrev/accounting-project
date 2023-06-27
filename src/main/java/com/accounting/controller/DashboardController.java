package com.accounting.controller;


import com.accounting.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final InvoiceService invoiceService;

    public DashboardController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/dashboard")
    public String getDashBoard(Model model) {

        model.addAttribute("invoices",invoiceService.findLastThreeInvoices() );


        return "dashboard";


    }
}
