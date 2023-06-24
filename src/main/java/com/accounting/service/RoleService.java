package com.accounting.service;

import com.accounting.dto.RoleDto;
import com.accounting.service.common.CrudService;

import java.util.List;

public interface RoleService extends CrudService<RoleDto, Long> {

    List<RoleDto> getRolesForCurrentUser();
}
