package com.nogayhusrev.accounting.service;

import com.nogayhusrev.accounting.dto.UserDto;
import com.nogayhusrev.accounting.service.common.CrudService;

public interface UserService extends CrudService<UserDto, Long> {
    UserDto findByUsername(String name);

    UserDto getCurrentUser();

}
