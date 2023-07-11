package com.nogayhusrev.accounting.service;

import com.nogayhusrev.accounting.dto.RoleDto;
import com.nogayhusrev.accounting.service.common.CrudService;

import java.util.List;

public interface RoleService extends CrudService<RoleDto, Long> {

    List<RoleDto> getRolesForCurrentUser();
}
