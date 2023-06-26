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
    public AddressDto findById(Long addressId) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public List<AddressDto> findAll() {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public void save(AddressDto addressDto) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public void delete(Long addressId) {

    }

    @Override
    public void update(AddressDto addressDto, Long addressId) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public boolean isExist(AddressDto addressDto) {
        return false;
    }

}
