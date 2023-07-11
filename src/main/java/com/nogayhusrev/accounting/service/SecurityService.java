package com.nogayhusrev.accounting.service;

import com.nogayhusrev.accounting.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    UserDto getCurrentUser();
}
