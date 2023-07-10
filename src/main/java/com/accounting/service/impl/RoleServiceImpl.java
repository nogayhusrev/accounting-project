package com.accounting.service.impl;

import com.accounting.dto.RoleDto;
import com.accounting.entity.Role;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.RoleRepository;
import com.accounting.service.RoleService;
import com.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;
    private final UserService userService;

    public RoleServiceImpl(RoleRepository roleRepository, MapperUtil mapperUtil, UserService userService) {
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }


    @Override
    public RoleDto findById(Long roleId) {
        return mapperUtil.convert(roleRepository.findRoleById(roleId), new RoleDto());
    }

    @Override
    public List<RoleDto> getRolesForCurrentUser() {

        List<Role> roles;

        if (userService.getCurrentUser().getRole().getDescription().equals("Root User")) {
            roles = roleRepository.findAll().stream()
                    .filter(role -> role.getDescription().equals("Admin")).collect(Collectors.toList());
        } else {
            roles = roleRepository.findAll().stream()
                    .filter(role -> !role.getDescription().equals("Root User"))
                    .collect(Collectors.toList());
        }

        return roles.stream().map(role -> mapperUtil.convert(role, new RoleDto())).collect(Collectors.toList());
    }


    @Override
    public List<RoleDto> findAll() {
        throw new IllegalStateException("Not Implemented");
    }

    @Override
    public RoleDto findByName(String name) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }

    @Override
    public void save(RoleDto roleDto) {
        throw new IllegalStateException("Not Implemented");

    }

    @Override
    public void delete(Long roleId) {
        throw new IllegalStateException("Not Implemented");

    }

    @Override
    public void update(RoleDto roleDto, Long roleId) {
        throw new IllegalStateException("Not Implemented");

    }

    @Override
    public boolean isExist(RoleDto roleDto, Long roleId) {
        throw new IllegalStateException("NOT IMPLEMENTED");

    }

    @Override
    public boolean isExist(RoleDto roleDto) {
        throw new IllegalStateException("NOT IMPLEMENTED");
    }
}
