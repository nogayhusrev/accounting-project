package com.accounting.service;

import com.accounting.dto.CompanyDto;
import com.accounting.service.common.CrudService;

import java.util.List;

public interface CompanyService extends CrudService<CompanyDto, Long> {
    void activate(Long companyId);

    void deactivate(Long companyId);

    List<CompanyDto> getCompaniesForCurrentUser();
}
