package com.accounting.service.impl;


import com.accounting.dto.UserDto;
import com.accounting.entity.Company;
import com.accounting.entity.User;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.UserRepository;
import com.accounting.service.SecurityService;
import com.accounting.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final SecurityService securityService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, @Lazy SecurityService securityService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.securityService = securityService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto findById(Long id) {
        return mapperUtil.convert(userRepository.findUserById(id), new UserDto());
    }

    @Override
    public UserDto findByUsername(String username) {
        return mapperUtil.convert(userRepository.findUserByUsername(username), new UserDto());
    }

    @Override
    public UserDto getCurrentUser() {
        return securityService.getCurrentUser();
    }


    @Override
    public List<UserDto> findAll() {

        List<User> userList;
        if (getCurrentUser().getRole().getDescription().equals("Root User")) {
            userList = userRepository.findAllByRole_Description("Admin");
        } else {
            userList = userRepository.findAllByCompany(mapperUtil.convert(securityService.getCurrentUser().getCompany(), new Company()));
        }
        return userList.stream()
                .sorted(Comparator.comparing((User u) -> u.getCompany().getTitle()).thenComparing(u -> u.getRole().getDescription()))
                .map(user -> mapperUtil.convert(user,new UserDto()))
                .map(userDto -> {
                    userDto.setIsOnlyAdmin(isOnlyAdmin(userDto));
                     return userDto;
                })
                .collect(Collectors.toList());


    }

    @Override
    public void save(UserDto userDto) {
        User user = mapperUtil.convert(userDto, new User());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);

    }

    @Override
    public void delete(UserDto userDto) {
        throw new IllegalStateException();
    }

    @Override
    public void update(UserDto userDto, Long id) {
        throw new IllegalStateException();
    }

    @Override
    public boolean isExist(UserDto userDto) {
        throw new IllegalStateException();
    }


    private boolean isOnlyAdmin(UserDto userDto) {
        return userRepository.findAllByCompany(mapperUtil.convert(userDto.getCompany(), new Company())).stream()
                .filter(user -> user.getRole().getDescription().equals("Admin"))
                .count() == 1 ;
    }


}
