package com.accounting.service.impl;


import com.accounting.dto.UserDto;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.UserRepository;
import com.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public UserDto findById(Long id) {
        return mapperUtil.convert(userRepository.findUserById(id), new UserDto());
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapperUtil.convert(user, new UserDto()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }

    @Override
    public void delete(UserDto userDto) {

    }

    @Override
    public void update(UserDto userDto) {

    }
}
