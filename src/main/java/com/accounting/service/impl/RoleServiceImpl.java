package com.accounting.service.impl;

import com.accounting.dto.RoleDto;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.RoleRepository;
import com.accounting.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public RoleDto findRoleById(Long id) {
        return mapperUtil.convert(roleRepository.findRoleById(id),new RoleDto());
    }

    @Override
    public List<RoleDto> getRolesForCurrentUser() {
        return null;
    }


}