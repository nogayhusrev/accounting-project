package com.accounting.service.impl;

import com.nogayhusrev.accounting.dto.addressApi.Country;
import com.nogayhusrev.accounting.service.impl.AddressServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
class AddressServiceImplTest {

    @Autowired
    AddressServiceImpl addressService;

    @Test
    @DisplayName("Get Countries And States From Api")
    void getAllCountries_Test() {

        Country country = new Country();

        country.setName("Turkey");
        country.setIso2("TR");
        country.setIso3("TUR");



        var countries = addressService.getAllCountries();
        Assertions.assertThat(countries).hasSizeGreaterThan(50);
        Assertions.assertThat(countries).contains(country);
    }
}