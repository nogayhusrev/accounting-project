package com.accounting.service;

import com.accounting.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    UserDto getCurrentUser();
}
