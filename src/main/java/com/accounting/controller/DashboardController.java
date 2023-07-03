package com.accounting.controller;


import com.accounting.service.CompanyService;
import com.accounting.service.DashboardService;
import com.accounting.service.InvoiceService;
import com.accounting.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final InvoiceService invoiceService;
    private final DashboardService dashboardService;
    private final UserService userService;


    public DashboardController(InvoiceService invoiceService, DashboardService dashboardService, CompanyService companyService, UserService userService) {
        this.invoiceService = invoiceService;
        this.dashboardService = dashboardService;

        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getDashBoard(Model model) {



        model.addAttribute("companyTitle", userService.getCurrentUser().getCompany().getTitle());
        model.addAttribute("summaryNumbers", dashboardService.getSummaryNumbers());
        model.addAttribute("invoices", invoiceService.findLastThreeInvoices());
        model.addAttribute("exchangeRates", dashboardService.getExchangeRates());
        model.addAttribute("title", "Ngy Accounting-Dashboard");
        return "dashboard";





    }
}
