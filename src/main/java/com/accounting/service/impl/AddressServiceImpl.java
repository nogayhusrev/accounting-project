package com.accounting.service.impl;

import com.accounting.dto.AddressDto;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.AddressRepository;
import com.accounting.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final MapperUtil mapperUtil;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public AddressDto findById(Long id) {
        return null;
    }

    @Override
    public List<AddressDto> findAll() {
        return null;
    }

    @Override
    public AddressDto save(AddressDto data) {
        return null;
    }

    @Override
    public void delete(AddressDto data) {

    }

    @Override
    public void update(AddressDto data) {

    }
}
