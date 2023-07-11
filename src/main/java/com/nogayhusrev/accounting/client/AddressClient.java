package com.nogayhusrev.accounting.client;


import com.nogayhusrev.accounting.dto.addressApi.CountriesAndStatesGETResponse;
import com.nogayhusrev.accounting.dto.addressApi.CountryAndItsStatesPOSTRequest;
import com.nogayhusrev.accounting.dto.addressApi.CountryAndItsStatesPOSTResponse;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "address-api", url = "https://countriesnow.space/api/v0.1/countries/states")
public interface AddressClient {


    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    CountriesAndStatesGETResponse getCountriesAndStatesGETResponse();


    @PostMapping
    @Headers("Content-Type: application/json")
    @RequestLine("POST /states")
    CountryAndItsStatesPOSTResponse getCountryAndItsStatesPOSTResponse(@SpringQueryMap CountryAndItsStatesPOSTRequest countryAndItsStatesPOSTRequest);

}
