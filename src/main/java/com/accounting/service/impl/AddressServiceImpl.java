package com.accounting.service.impl;

import com.accounting.client.AddressClient;
import com.accounting.dto.addressApi.AddressApiCountryResponse;
import com.accounting.dto.addressApi.Country;
import com.accounting.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<Country> countries = new ArrayList<>();

        AddressApiCountryResponse addressApiCountryResponse = addressClient.getAddressApiCountryResponse();

        countries.addAll(addressApiCountryResponse.getData());

        return countries;
    }

    @Override
    public List<String> getAllCities() {
        return null;
    }

    @Override
    public List<String> getCitiesOfSelectedCountry(String country) {
        return null;
    }

    @Override
    public List<String> getAllStates() {
        return null;
    }
}
