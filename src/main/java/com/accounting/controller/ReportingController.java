package com.accounting.controller;


import com.accounting.service.ReportingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }


    @GetMapping("/stock")
    public String getStock(Model model) {

        model.addAttribute("invoiceProducts", reportingService.getStock());


        return "/report/stock-report";
    }



}
