package com.accounting.converter;

import com.accounting.dto.UserDto;
import com.accounting.service.UserService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class UserDtoConverter implements Converter<String, UserDto> {

    private final UserService userService;

    public UserDtoConverter(@Lazy UserService userService) {
        this.userService = userService;
    }


    //    @SneakyThrows
    @Override
    public UserDto convert(String id) {
        // it throws error if user selects "Select" even with @SneakyThrows
        if (id == null || id.isBlank())
            return null;
        return userService.findById(Long.parseLong(id));
    }

}