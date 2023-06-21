package com.accounting.service;

import com.accounting.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto findRoleById(Long id);

    List<RoleDto> getRolesForCurrentUser();
}
