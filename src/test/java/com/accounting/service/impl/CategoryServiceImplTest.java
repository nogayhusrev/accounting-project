package com.accounting.service.impl;

import com.accounting.dto.CategoryDto;
import com.accounting.dto.CompanyDto;
import com.accounting.dto.UserDto;
import com.accounting.entity.Category;
import com.accounting.entity.Company;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.CategoryRepository;
import com.accounting.service.CategoryService;
import com.accounting.service.SecurityService;
import com.accounting.service.UserService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    CategoryRepository repository;

    @Mock
    UserService userService;

    @Mock
    MapperUtil mapperUtil;

    @InjectMocks
    CategoryServiceImpl service;


    @Test
    @DisplayName("Get Category From DB by Id")
    void findById_Test() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(new Category()));
        when(mapperUtil.convert(any(Category.class), any(CategoryDto.class))).thenReturn(new CategoryDto());
        service.findById(anyLong());
        verify(repository).findById(anyLong());

    }

    @Test
    void findAll_Test() {

        CompanyDto companyDto = new CompanyDto();
        UserDto userDto = new UserDto();
        userDto.setCompany(companyDto);

        when(userService.getCurrentUser()).thenReturn(userDto);

        service.findAll();

        verify(repository).findAllByCompany(mapperUtil.convert(userDto.getCompany(),new Company()));


    }

    @Test
    void save_Test() {


        CompanyDto companyDto = new CompanyDto();
        UserDto userDto = new UserDto();
        userDto.setCompany(companyDto);


        when(userService.getCurrentUser()).thenReturn(userDto);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCompany(userDto.getCompany());
        categoryDto.setId(anyLong());
        categoryDto.setDescription(anyString());
        categoryDto.setHasProduct(false);

        service.save(categoryDto);


        Category category = mapperUtil.convert(categoryDto,new Category());


        verify(repository).save(category);


    }

    @Test
    void delete() {

        CompanyDto companyDto = new CompanyDto();
        UserDto userDto = new UserDto();
        userDto.setCompany(companyDto);

        when(userService.getCurrentUser()).thenReturn(userDto);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCompany(userDto.getCompany());
        categoryDto.setId(anyLong());
        categoryDto.setDescription(anyString());
        categoryDto.setHasProduct(false);

        service.delete(categoryDto.getId());


        Category category = new Category();
        category.setCompany(mapperUtil.convert(userDto.getCompany(),new Company()));


    }

    @Test
    void update() {
    }

    @Test
    void isExist() {
        CompanyDto companyDto = new CompanyDto();
        UserDto userDto = new UserDto();
        userDto.setCompany(companyDto);

        when(userService.getCurrentUser()).thenReturn(userDto);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCompany(userDto.getCompany());
        categoryDto.setId(3L);
        categoryDto.setDescription("Phone");
        categoryDto.setHasProduct(false);

        service.isExist(categoryDto);

        verify(repository).findById(categoryDto.getId());


    }

    @Test
    void testIsExist() {
    }
}