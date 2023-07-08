package com.accounting.service.impl;

import com.accounting.client.AddressClient;
import com.accounting.dto.addressApi.Country;
import com.accounting.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressClient addressClient;

    public AddressServiceImpl(AddressClient addressClient) {
        this.addressClient = addressClient;
    }


    @Override
    public List<Country> getAllCountries() {

        List<Country> countries = addressClient.getCountriesAndStatesGETResponse().getCountries();

        countries =countries.stream().sorted(Comparator.comparing(country -> country.name)).collect(Collectors.toList());

        return countries;
    }

}
