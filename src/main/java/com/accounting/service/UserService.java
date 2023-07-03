package com.accounting.service;

import com.accounting.dto.UserDto;
import com.accounting.service.common.CrudService;

public interface UserService extends CrudService<UserDto, Long> {
    UserDto findByUsername(String name);

    UserDto getCurrentUser();

}
