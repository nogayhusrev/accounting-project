package com.nogayhusrev.accounting.service.impl;


import com.nogayhusrev.accounting.dto.UserDto;
import com.nogayhusrev.accounting.entity.User;
import com.nogayhusrev.accounting.entity.common.UserPrincipal;
import com.nogayhusrev.accounting.repository.UserRepository;
import com.nogayhusrev.accounting.service.SecurityService;
import com.nogayhusrev.accounting.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final UserService userService;

    public SecurityServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException(username);

        return new UserPrincipal(user);

    }

    @Override
    public UserDto getCurrentUser() {

        return userService.findByUsername(SecurityContextHolder.getContext().
                getAuthentication().getName());
    }
}
