package com.accounting.controller;


import com.accounting.service.DashboardService;
import com.accounting.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final InvoiceService invoiceService;
    private final DashboardService dashboardService;

    public DashboardController(InvoiceService invoiceService, DashboardService dashboardService) {
        this.invoiceService = invoiceService;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String getDashBoard(Model model) {

        model.addAttribute("invoices", invoiceService.findLastThreeInvoices());
        model.addAttribute("exchangeRates", dashboardService.getExchangeRates());


        return "dashboard";


    }
}
