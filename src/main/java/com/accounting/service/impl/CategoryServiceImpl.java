package com.accounting.service.impl;

import com.accounting.dto.CategoryDto;
import com.accounting.entity.Category;
import com.accounting.entity.Company;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.CategoryRepository;
import com.accounting.service.CategoryService;
import com.accounting.service.SecurityService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final MapperUtil mapperUtil;

    private final SecurityService securityService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil, SecurityService securityService) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;

        this.securityService = securityService;
    }


    @Override
    public CategoryDto findById(Long categoryId) {
        return mapperUtil.convert(categoryRepository.findById(categoryId).get(),new CategoryDto());
    }

    @Override
    public List<CategoryDto> findAll() {
        Company company = mapperUtil.convert(securityService.getCurrentUser().getCompany(), new Company());
        return categoryRepository
                .findAllByCompany(company)
                .stream()
                .sorted(Comparator.comparing(Category::getDescription))
                .map(category -> mapperUtil.convert(category, new CategoryDto())).collect(Collectors.toList());
    }

    @Override
    public void save(CategoryDto categoryDto) {

        Category category = mapperUtil.convert(categoryDto, new Category());
        Company company = mapperUtil.convert(securityService.getCurrentUser().getCompany(), new Company());
        category.setCompany(company);

        categoryRepository.save(category);

    }

    @Override
    public void delete(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId()).get();

        category.setIsDeleted(true);
        category.setDescription(category.getDescription() + " " + category.getId() + " DELETED");
        categoryRepository.save(category);
    }

    @Override
    public void update(CategoryDto categoryDto, Long categoryId) {
        categoryDto.setId(categoryId);
        save(categoryDto);
    }

    @Override
    public boolean isExist(CategoryDto categoryDto) {
        return categoryRepository.findAll().stream().filter(category -> category.getDescription().equalsIgnoreCase(categoryDto.getDescription())).count() > 0;
    }
}
