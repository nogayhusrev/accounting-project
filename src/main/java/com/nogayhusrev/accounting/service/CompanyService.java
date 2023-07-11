package com.nogayhusrev.accounting.service;

import com.nogayhusrev.accounting.dto.CompanyDto;
import com.nogayhusrev.accounting.service.common.CrudService;

import java.util.List;

public interface CompanyService extends CrudService<CompanyDto, Long> {
    void activate(Long companyId);

    void deactivate(Long companyId);

    List<CompanyDto> getCompaniesForCurrentUser();
}
