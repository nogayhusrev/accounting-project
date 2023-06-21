package com.accounting.service.impl;


import com.accounting.dto.CompanyDto;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.CompanyRepository;
import com.accounting.service.CompanyService;
import org.springframework.stereotype.Service;
import com.accounting.entity.Company;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final MapperUtil mapperUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public CompanyDto findById(Long id) {
        return mapperUtil.convert(companyRepository.findById(id).get(),new CompanyDto());
    }

    @Override
    public List<CompanyDto> findAll() {
        return companyRepository.findAll()
                .stream()
                .filter(company -> company.getId() != 1)
                .sorted(Comparator.comparing(Company::getCompanyStatus).thenComparing(Company::getTitle))
                .map(each -> mapperUtil.convert(each, new CompanyDto()))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto save(CompanyDto companyDto) {
        return null;
    }

    @Override
    public void delete(CompanyDto companyDto) {

    }

    @Override
    public void update(CompanyDto companyDto) {

    }
}
