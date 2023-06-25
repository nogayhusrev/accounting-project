package com.accounting.service.impl;

import com.accounting.dto.CategoryDto;
import com.accounting.entity.Category;
import com.accounting.entity.Company;
import com.accounting.mapper.MapperUtil;
import com.accounting.repository.CategoryRepository;
import com.accounting.service.CategoryService;
import com.accounting.service.ProductService;
import com.accounting.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final MapperUtil mapperUtil;

    private final UserService userService;
    private final ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil, UserService userService, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
        this.productService = productService;
    }


    @Override
    public CategoryDto findById(Long categoryId) {
        return mapperUtil.convert(categoryRepository.findById(categoryId).get(), new CategoryDto());
    }

    @Override
    public List<CategoryDto> findAll() {
        Company company = mapperUtil.convert(userService.getCurrentUser().getCompany(), new Company());
        return categoryRepository
                .findAllByCompany(company)
                .stream()
                .sorted(Comparator.comparing(Category::getDescription))
                .map(category -> {

                    CategoryDto categoryDto = mapperUtil.convert(category, new CategoryDto());
                    if (hasProducts(category))
                        categoryDto.setHasProduct(true);

                    return categoryDto;

                }).collect(Collectors.toList());
    }

    @Override
    public void save(CategoryDto categoryDto) {

        Category category = mapperUtil.convert(categoryDto, new Category());
        Company company = mapperUtil.convert(userService.getCurrentUser().getCompany(), new Company());
        category.setCompany(company);

        categoryRepository.save(category);

    }

    @Override
    public void delete(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId()).get();

        if (hasProducts(category))
            return;

        category.setIsDeleted(true);
        category.setDescription(category.getDescription() + "_" + category.getId() + "_DELETED");
        categoryRepository.save(category);
    }

    private boolean hasProducts(Category category) {
        return productService.findAll().stream()
                        .filter(productDto -> productDto.getCategory().getDescription().equalsIgnoreCase(category.getDescription()))
                        .count() > 0;
    }

    @Override
    public void update(CategoryDto categoryDto, Long categoryId) {
        categoryDto.setId(categoryId);
        save(categoryDto);
    }

    @Override
    public boolean isExist(CategoryDto categoryDto) {
        return findAll().stream().filter(category -> category.getDescription().equalsIgnoreCase(categoryDto.getDescription())).count() > 0;
    }
}
