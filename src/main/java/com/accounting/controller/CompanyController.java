package com.accounting.controller;


import com.accounting.dto.CompanyDto;
import com.accounting.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("companies",companyService.findAll());

        return "/company/company-list";
    }


    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("newCompany", new CompanyDto());

        return "/company/company-create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newCompany") CompanyDto companyDto, BindingResult bindingResult) {

        if (companyService.isExist(companyDto)) {
            bindingResult.rejectValue("title", " ", "This title already exists.");
        }


        if (bindingResult.hasErrors()) {
            return "company/company-create";
        }

        companyService.save(companyDto);

        return "redirect:/companies/list";

    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,Model model){

        model.addAttribute("company",companyService.findById(id));

        return "/company/company-update";

    }

    @PostMapping("/update/{companyId}")
    public String update(@Valid @ModelAttribute("company") CompanyDto companyDto, BindingResult bindingResult, @PathVariable Long companyId, Model model) throws CloneNotSupportedException {


        if (companyService.isExist(companyDto)) {
            bindingResult.rejectValue("title", " ", "This title already exists.");
        }

        if (bindingResult.hasErrors()) {
            companyDto.setId(companyId);
            return "company/company-update";
        }

        companyService.update(companyDto, companyId);
        return "redirect:/companies/list";
    }

    @GetMapping("/activate/{companyId}")
    public String activate(@PathVariable("companyId") Long companyId) {
        companyService.activate(companyId);
        return "redirect:/companies/list";
    }

    @GetMapping("/deactivate/{companyId}")
    public String deactivate(@PathVariable("companyId") Long companyId) {
        companyService.deactivate(companyId);
        return "redirect:/companies/list";
    }
}
