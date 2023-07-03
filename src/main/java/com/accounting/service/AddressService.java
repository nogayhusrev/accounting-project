package com.accounting.service;

import com.accounting.dto.addressApi.Country;

import java.util.List;

public interface AddressService {

     List<Country> getAllCountries();
     List<String> getAllCities();
     List<String> getCitiesOfSelectedCountry(String country);
     List<String> getAllStates();
}
