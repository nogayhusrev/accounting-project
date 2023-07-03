package com.accounting.controller;


import com.accounting.client.AddressClient;
import com.accounting.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/addresses")
public class AddressController {

   private final AddressService addressService;
   private final AddressClient addressClient;

    public AddressController(AddressService addressService, AddressClient addressClient) {
        this.addressService = addressService;
        this.addressClient = addressClient;
    }

   

}
