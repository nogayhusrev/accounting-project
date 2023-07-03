package com.accounting.service.impl;

import com.accounting.dto.CategoryDto;
import com.accounting.entity.Category;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.CategoryRepository;
import com.accounting.service.CategoryService;
import com.accounting.service.SecurityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    CategoryRepository repository;

    @Mock
    SecurityService securityService;

    @Mock
    MapperUtil mapperUtil;

    @InjectMocks
    CategoryServiceImpl service;


    @Test
    @DisplayName("Get Category From DB by Id")
    void findById() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(new Category()));
        when(mapperUtil.convert(any(Category.class), any(CategoryDto.class))).thenReturn(new CategoryDto());
        service.findById(anyLong());
        verify(repository).findById(anyLong());

    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void isExist() {
    }

    @Test
    void testIsExist() {
    }
}