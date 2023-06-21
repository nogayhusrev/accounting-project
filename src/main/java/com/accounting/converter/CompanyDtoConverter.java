package com.accounting.converter;

import com.accounting.dto.CompanyDto;
import com.accounting.dto.RoleDto;
import com.accounting.service.CompanyService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CompanyDtoConverter implements Converter<String, CompanyDto> {

    private final CompanyService companyService;

    public CompanyDtoConverter(@Lazy CompanyService companyService) {
        this.companyService = companyService;
    }


    //    @SneakyThrows
    @Override
    public CompanyDto convert(String id){
        // it throws error if user selects "Select" even with @SneakyThrows
        if (id == null || id.isBlank())
            return null;
        return companyService.findById(Long.parseLong(id));
    }

}