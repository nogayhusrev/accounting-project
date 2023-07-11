package com.nogayhusrev.accounting.converter;

import com.nogayhusrev.accounting.dto.ClientVendorDto;
import com.nogayhusrev.accounting.service.ClientVendorService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ClientVendorDtoConverter implements Converter<String, ClientVendorDto> {

    private final ClientVendorService clientVendorService;

    public ClientVendorDtoConverter(@Lazy ClientVendorService clientVendorService) {
        this.clientVendorService = clientVendorService;
    }

    @Override
    public ClientVendorDto convert(String id) {
        // it throws error if user selects "Select" even with @SneakyThrows
        if (id == null || id.isBlank())
            return null;
        return clientVendorService.findById(Long.parseLong(id));
    }

}