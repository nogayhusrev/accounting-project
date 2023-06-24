package com.accounting.service.impl;

import com.accounting.dto.ClientVendorDto;
import com.accounting.entity.ClientVendor;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.ClientVendorRepository;
import com.accounting.service.ClientVendorService;
import com.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientVendorServiceImpl implements ClientVendorService {

    private final ClientVendorRepository clientVendorRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public ClientVendorServiceImpl(ClientVendorRepository clientVendorRepository, MapperUtil mapperUtil, UserService userService) {
        this.clientVendorRepository = clientVendorRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }

    @Override
    public ClientVendorDto findById(Long aLong) {
        throw new IllegalStateException("Illegal Method Call");
    }

    @Override
    public List<ClientVendorDto> findAll() {
        return clientVendorRepository.findAll().stream()
                .map(clientVendor -> mapperUtil.convert(clientVendor, new ClientVendorDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ClientVendorDto clientVendorDto) {

        clientVendorDto.setCompany(userService.getCurrentUser().getCompany());
        clientVendorRepository.save(mapperUtil.convert(clientVendorDto, new ClientVendor()));
    }

    @Override
    public void delete(ClientVendorDto clientVendorDto) {
        throw new IllegalStateException("Illegal Method Call");
    }

    @Override
    public void update(ClientVendorDto clientVendorDto, Long aLong) {
        throw new IllegalStateException("Illegal Method Call");
    }

    @Override
    public boolean isExist(ClientVendorDto clientVendorDto) {
        return clientVendorRepository.findAll().stream()
                .filter(clientVendor -> clientVendor.getClientVendorName().equalsIgnoreCase(clientVendorDto.getClientVendorName()))
                .count() > 0;
    }
}
