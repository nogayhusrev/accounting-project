package com.accounting.controller;


import com.accounting.client.AddressClient;
import com.accounting.service.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressClient addressClient;

    public AddressController(AddressService addressService, AddressClient addressClient) {
        this.addressService = addressService;
        this.addressClient = addressClient;
    }

//    @GetMapping("/list")
//    public ResponseEntity<AddressApiCountryStateResponse> list() {
////        AddressApiStateRequest addressApiStateRequest = new AddressApiStateRequest();
////        addressApiStateRequest.setCountry("Turkey");
////
////        AddressApiStateResponse addressApiStateResponse = addressClient.getAddressApiStateResponse(addressApiStateRequest);
//        return ResponseEntity.ok(addressClient.getAddressApiCountryStateResponse());
//    }


}
