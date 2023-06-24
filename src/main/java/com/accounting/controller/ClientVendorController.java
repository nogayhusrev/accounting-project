package com.accounting.controller;


import com.accounting.dto.ClientVendorDto;
import com.accounting.dto.CompanyDto;
import com.accounting.enums.ClientVendorType;
import com.accounting.service.ClientVendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/clientVendors")
public class ClientVendorController {

    private final ClientVendorService clientVendorService;

    public ClientVendorController(ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("clientVendors", clientVendorService.findAll());

        return "/clientVendor/clientVendor-list";
    }

    @GetMapping("/create")
    public String create(Model model){


        model.addAttribute("newClientVendor", new ClientVendorDto());
        model.addAttribute("clientVendorTypes", new ArrayList<ClientVendorType>(Arrays.asList(ClientVendorType.CLIENT,ClientVendorType.VENDOR)));


        return "/clientVendor/clientVendor-create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newClientVendor") ClientVendorDto clientVendorDto, BindingResult bindingResult, Model model) {

        if (clientVendorService.isExist(clientVendorDto)) {
            bindingResult.rejectValue("clientVendorName", " ", "This title already exists.");
        }


        if (bindingResult.hasErrors()) {
            model.addAttribute("clientVendorTypes", new ArrayList<ClientVendorType>(Arrays.asList(ClientVendorType.CLIENT,ClientVendorType.VENDOR)));


            return "/clientVendor/clientVendor-create";
        }

        clientVendorService.save(clientVendorDto);

        return "redirect:/clientVendors/list";

    }

    @GetMapping("/update/{clientVendorId}")
    public String update(@PathVariable("clientVendorId") Long clientVendorId, Model model){

        model.addAttribute("clientVendor",clientVendorService.findById(clientVendorId));
        model.addAttribute("clientVendorTypes", new ArrayList<ClientVendorType>(Arrays.asList(ClientVendorType.CLIENT,ClientVendorType.VENDOR)));


        return "/clientVendor/clientVendor-update";

    }

    @PostMapping("/update/{clientVendorId}")
    public String update(@Valid @ModelAttribute("clientVendorId") ClientVendorDto clientVendorDto, BindingResult bindingResult, @PathVariable Long clientVendorId, Model model) throws CloneNotSupportedException {



        if (bindingResult.hasErrors()) {
            clientVendorDto.setId(clientVendorId);
            return "redirect:/clientVendors/update/" + clientVendorId;
        }

        clientVendorService.update(clientVendorDto, clientVendorId);
        return "redirect:/clientVendors/list";
    }

    @GetMapping("/delete/{clientVendorId}")
    public String deleteUser(@PathVariable("clientVendorId") Long clientVendorId){
        clientVendorService.delete(clientVendorService.findById(clientVendorId));
        return "redirect:/clientVendors/list";
    }

}
