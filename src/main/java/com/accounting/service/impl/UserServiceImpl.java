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
    private final PasswordEncoder passwordEncoder;

    private final SecurityService securityService;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, PasswordEncoder passwordEncoder, @Lazy SecurityService securityService) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
    }


    @Override
    public UserDto findById(Long userId) {
        return mapperUtil.convert(userRepository.findUserById(userId), new UserDto());
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
                .map(user -> mapperUtil.convert(user, new UserDto()))
                .map(userDto -> {
                    isOnlyAdmin(userDto);
                    return userDto;
                })
                .collect(Collectors.toList());


    }

    @Override
    public UserDto findByName(String username) {
        User user = userRepository.findAll().stream()
                .filter(savedUser -> savedUser.getUsername().equalsIgnoreCase(username))
                .findFirst().get();

        return mapperUtil.convert(user, new UserDto());
    }

    @Override
    public void save(UserDto userDto) {
        User user = mapperUtil.convert(userDto, new User());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);

    }

    @Override
    public void delete(Long userId) {

        UserDto userDto = findById(userId);

        if (userDto.getRole().getDescription().equals("Admin") && adminCount(userDto) == 1)
            throw new IllegalStateException("USER IS ONLY ADMIN, CANNOT BE DELETED");

        User user = userRepository.findById(userId).get();
        user.setUsername(user.getUsername() + "-" + user.getId() + " DELETED");


        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public void update(UserDto userDto, Long userId) {
        User user = userRepository.findUserById(userId);
        userDto.setId(user.getId());
        userRepository.save(mapperUtil.convert(userDto, new User()));
    }

    @Override
    public boolean isExist(UserDto userDto, Long userId) {
        Long idCheck = userRepository.findAll().stream()
                .filter(savedUser -> savedUser.getUsername().equalsIgnoreCase(userDto.getUsername()))
                .filter(savedUser -> savedUser.getId() != userId)
                .count();

        return idCheck > 0;
    }

    @Override
    public boolean isExist(UserDto userDto) {
        return userRepository.findAll().stream()
                .filter(savedUser -> savedUser.getUsername().equalsIgnoreCase(userDto.getUsername()))
                .count() > 0;
    }

    private int adminCount(UserDto userDto) {
        return (int) userRepository.findAllByCompany(mapperUtil.convert(userDto.getCompany(), new Company())).stream()
                .filter(user -> user.getRole().getDescription().equals("Admin"))
                .count();
    }


    private void isOnlyAdmin(UserDto userDto) {
        userDto.setIsOnlyAdmin(userDto.getRole().getDescription().equalsIgnoreCase("Admin") && adminCount(userDto) == 1);
    }

}
