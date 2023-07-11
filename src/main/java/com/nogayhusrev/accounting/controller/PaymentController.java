package com.nogayhusrev.accounting.controller;

import com.nogayhusrev.accounting.dto.PaymentDto;
import com.nogayhusrev.accounting.entity.common.ChargeRequest;
import com.nogayhusrev.accounting.service.PaymentService;
import com.nogayhusrev.accounting.service.impl.StripeServiceImpl;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private final StripeServiceImpl stripeServiceImpl;
    private final PaymentService paymentService;

    public PaymentController(StripeServiceImpl stripeServiceImpl, PaymentService paymentService) {
        this.stripeServiceImpl = stripeServiceImpl;
        this.paymentService = paymentService;
    }


    @GetMapping({"/list", "/list/{year}"})
    public String list(@RequestParam(value = "year", required = false) String selectedYear, Model model) {

        int selectedYear1 = (selectedYear == null || selectedYear.isEmpty()) ? LocalDate.now().getYear() : Integer.parseInt(selectedYear);
        paymentService.createPaymentsIfNotExist(selectedYear1);
        model.addAttribute("payments",paymentService.getAllPaymentsByYear(selectedYear1));
        model.addAttribute("year", selectedYear1);
        return "payment/payment-list";
    }


    @GetMapping("/newPayment/{id}")
    public String checkout(@PathVariable("id") Long id, Model model) {

        PaymentDto dto = paymentService.getPaymentById(id);
        model.addAttribute("payment", dto);
        model.addAttribute("amount", dto.getAmount() * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        model.addAttribute("modelId", id);
        return "payment/payment-method";
    }


    @PostMapping("/charge/{id}")
    public String charge(ChargeRequest chargeRequest, @PathVariable("id") Long id)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        stripeServiceImpl.charge(chargeRequest);
        paymentService.payPayment(id);
        return "redirect:/payments/list";
    }




}