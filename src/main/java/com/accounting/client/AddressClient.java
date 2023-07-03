package com.accounting.client;


import com.accounting.dto.addressApi.AddressApiCountryStateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "address-api", url = "https://countriesnow.space/api/v0.1/countries/")
public interface AddressClient {


    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    AddressApiCountryStateResponse getAddressApiCountryStateResponse();


//    @PostMapping(value = "/states", consumes = MediaType.APPLICATION_JSON_VALUE)
//    Respone getAddressApiCountryResponse(@RequestBody AddressApiCountryStateRequest addressApiCountryStateRequest);

}
